package ru.psbank.acquiringoffice.ui

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
import ru.psbank.acquiringoffice.R
import ru.psbank.acquiringoffice.databinding.FragmentTerminalBinding
import javax.inject.Inject
import javax.inject.Provider

internal class TerminalFragment : DaggerFragment(R.layout.fragment_terminal) {

    companion object {
        fun newInstance() = TerminalFragment()
    }

    @Inject
    lateinit var viewModelProvider: Provider<TerminalViewModel>

    private val viewModel: TerminalViewModel by viewModels {
        viewModelFactory {
            addInitializer(TerminalViewModel::class) {
                viewModelProvider.get()
            }
        }
    }

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
