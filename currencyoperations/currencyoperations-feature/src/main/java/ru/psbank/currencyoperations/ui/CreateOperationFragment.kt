package ru.psbank.currencyoperations.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.psbank.currencyoperations.R
import ru.psbank.currencyoperations.databinding.FragmentCreateOperationBinding
import ru.psbank.currencyoperations.ui.OperationCreationViewModel.FieldError

internal class CreateOperationFragment : Fragment(R.layout.fragment_create_operation) {
    companion object {
        fun newInstance() = CreateOperationFragment()
    }

    private val viewModel: OperationCreationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentCreateOperationBinding.bind(view)

        val emptyFieldError = getString(R.string.empty_field_error)

        viewModel.errors.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { errors ->
                binding.tilRate.error = emptyFieldError.takeIf { FieldError.RateBlank in errors }
                binding.tilToCurrency.error = emptyFieldError.takeIf {
                    FieldError.ToCurrencyBlank in errors
                }
                binding.tilFromCurrency.error = emptyFieldError.takeIf {
                    FieldError.FromCurrencyBlank in errors
                }
                binding.tilAmountFrom.error = emptyFieldError.takeIf {
                    FieldError.AmountFromBlank in errors
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        val textInputs = listOf(
            binding.tilAmountFrom,
            binding.tilFromCurrency,
            binding.tilRate,
            binding.tilToCurrency,
        )

        binding.tietAmountFrom.doAfterTextChanged {
            viewModel.changeAmountFrom(it?.toString())
        }

        binding.tietFromCurrency.doAfterTextChanged {
            viewModel.changeFromCurrency(it?.toString())
        }

        binding.tietRate.doAfterTextChanged {
            viewModel.changeRate(it?.toString())
        }

        binding.tietToCurrency.doAfterTextChanged {
            viewModel.changeToCurrency(it?.toString())
        }

        viewModel.viewState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                when (state) {
                    is OperationCreationViewState.Error -> {
                        binding.progress.isGone = true
                        binding.createOperation.isEnabled = true
                        binding.createOperation.setText(R.string.create_operation)
                        Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT)
                            .show()
                        viewModel.handleError()
                        textInputs.forEach { it.isEnabled = true }
                    }

                    OperationCreationViewState.Idle -> {
                        binding.progress.isGone = true
                        binding.createOperation.isEnabled = true
                        binding.createOperation.setText(R.string.create_operation)
                        textInputs.forEach { it.isEnabled = true }
                    }

                    is OperationCreationViewState.Submitted -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        parentFragmentManager.popBackStack()
                    }

                    OperationCreationViewState.Submitting -> {
                        binding.progress.isVisible = true
                        binding.createOperation.isEnabled = false
                        binding.createOperation.text = ""
                        textInputs.forEach { it.isEnabled = false }
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.createOperation.setOnClickListener {
            viewModel.submitOperation()
        }
    }
}
