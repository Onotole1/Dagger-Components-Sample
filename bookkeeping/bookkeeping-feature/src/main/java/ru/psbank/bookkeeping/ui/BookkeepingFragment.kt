package ru.psbank.bookkeeping.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.psbank.bookkeeping.R
import ru.psbank.bookkeeping.databinding.FragmentBookkeepingBinding
import javax.inject.Inject
import javax.inject.Provider

internal class BookkeepingFragment : DaggerFragment(R.layout.fragment_bookkeeping) {

    companion object {
        fun newInstance() = BookkeepingFragment()
    }

    @Inject
    lateinit var viewModelProvider: Provider<BookkeepingViewModel>

    private val viewModel: BookkeepingViewModel by viewModels {
        viewModelFactory {
            addInitializer(BookkeepingViewModel::class) {
                viewModelProvider.get()
            }
        }
    }

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
