package ru.psbank.bookkeeping.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.psbank.bookkeeping.R
import ru.psbank.bookkeeping.databinding.FragmentBookkeepingBinding

@AndroidEntryPoint
internal class BookkeepingFragment : Fragment(R.layout.fragment_bookkeeping) {

    companion object {
        fun newInstance() = BookkeepingFragment()
    }

    private val viewModel: BookkeepingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentBookkeepingBinding.bind(view)
        val adapter = BookkeepingAdapter()
        binding.list.adapter = adapter

        viewModel.viewState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                when (state) {
                    BookkeepingViewState.Error -> {
                        binding.progress.isGone = true
                        binding.errorGroup.isVisible = true
                        adapter.submitList(emptyList())
                    }
                    BookkeepingViewState.Loading -> {
                        binding.progress.isVisible = true
                        binding.errorGroup.isGone = true
                        adapter.submitList(emptyList())
                    }
                    is BookkeepingViewState.Success -> {
                        binding.progress.isGone = true
                        binding.errorGroup.isGone = true
                        adapter.submitList(state.transactions)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }
}
