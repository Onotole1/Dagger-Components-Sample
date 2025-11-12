package ru.psbank.acquiring.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.psbank.acquiring.R
import ru.psbank.acquiring.databinding.FragmentAcquiringBinding
import ru.psbank.acquiring.di.DaggerAcquiringFragmentComponent
import javax.inject.Inject
import javax.inject.Provider

internal class AcquiringFragment : Fragment(R.layout.fragment_acquiring) {

    companion object {
        fun newInstance() = AcquiringFragment()
    }

    @Inject
    lateinit var viewModelProvider: Provider<AcquiringViewModel>

    private val viewModel: AcquiringViewModel by viewModels {
        viewModelFactory {
            addInitializer(AcquiringViewModel::class) {
                viewModelProvider.get()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAcquiringFragmentComponent.create()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentAcquiringBinding.bind(view)
        val adapter = AcquiringAdapter()
        binding.list.adapter = adapter

        viewModel.viewState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                when (state) {
                    AcquiringViewState.Error -> {
                        binding.progress.isGone = true
                        binding.errorGroup.isVisible = true
                        adapter.submitList(emptyList())
                    }

                    AcquiringViewState.Loading -> {
                        binding.progress.isVisible = true
                        binding.errorGroup.isGone = true
                        adapter.submitList(emptyList())
                    }

                    is AcquiringViewState.Success -> {
                        binding.progress.isGone = true
                        binding.errorGroup.isGone = true
                        adapter.submitList(state.payments)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }
}
