package ru.psbank.currencyoperations.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.psbank.currencyoperations.R
import ru.psbank.currencyoperations.databinding.FragmentOperationsListBinding
import ru.psbank.currencyoperations.di.DaggerOperationsListFragmentComponent
import ru.psbank.utls.findComponentDependencies
import javax.inject.Inject
import javax.inject.Provider
import ru.psbank.mainscreen.core.R as mainscreencoreR

internal class OperationsListFragment : Fragment(R.layout.fragment_operations_list) {

    companion object {
        fun newInstance() = OperationsListFragment()
    }

    @Inject
    lateinit var viewModelProvider: Provider<OperationsListViewModel>

    private val viewModel: OperationsListViewModel by viewModels {
        viewModelFactory {
            addInitializer(OperationsListViewModel::class) {
                viewModelProvider.get()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerOperationsListFragmentComponent.builder()
            .operationsListFragmentDependencies(findComponentDependencies())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentOperationsListBinding.bind(view)
        val adapter = CurrencyOperationsAdapter()
        binding.list.adapter = adapter

        binding.fbAdd.setOnClickListener {
            parentFragmentManager.commit {
                replace(mainscreencoreR.id.container, CreateOperationFragment.newInstance())
                addToBackStack(null)
            }
        }

        viewModel.viewState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                when (state) {
                    OperationsListViewState.Error -> {
                        binding.progress.isGone = true
                        binding.errorGroup.isVisible = true
                        adapter.submitList(emptyList())
                    }
                    OperationsListViewState.Loading -> {
                        binding.progress.isVisible = true
                        binding.errorGroup.isGone = true
                        adapter.submitList(emptyList())
                    }
                    is OperationsListViewState.Success -> {
                        binding.progress.isGone = true
                        binding.errorGroup.isGone = true
                        adapter.submitList(state.operations)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }
}
