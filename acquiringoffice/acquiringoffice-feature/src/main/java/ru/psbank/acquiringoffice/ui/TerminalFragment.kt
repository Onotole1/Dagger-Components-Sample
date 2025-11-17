package ru.psbank.acquiringoffice.ui

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
import ru.psbank.acquiringoffice.R
import ru.psbank.acquiringoffice.databinding.FragmentTerminalBinding

@AndroidEntryPoint
internal class TerminalFragment : Fragment(R.layout.fragment_terminal) {

    companion object {
        fun newInstance() = TerminalFragment()
    }

    private val viewModel: TerminalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTerminalBinding.bind(view)
        val adapter = TerminalAdapter()
        binding.list.adapter = adapter

        viewModel.viewState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                when (state) {
                    TerminalViewState.Error -> {
                        binding.progress.isGone = true
                        binding.errorGroup.isVisible = true
                        adapter.submitList(emptyList())
                    }
                    TerminalViewState.Loading -> {
                        binding.progress.isVisible = true
                        binding.errorGroup.isGone = true
                        adapter.submitList(emptyList())
                    }
                    is TerminalViewState.Success -> {
                        binding.progress.isGone = true
                        binding.errorGroup.isGone = true
                        adapter.submitList(state.payments)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }
}
