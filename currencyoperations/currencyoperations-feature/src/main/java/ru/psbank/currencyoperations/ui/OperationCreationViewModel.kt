package ru.psbank.currencyoperations.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.psbank.currencyoperations.interactor.CurrencyOperationInteractor
import ru.psbank.currencyoperations.repository.CurrencyOperation
import ru.psbank.currencyoperations.repository.OperationStatus
import ru.psbank.currencyoperations.repository.OperationType
import java.time.LocalDateTime
import javax.inject.Inject

sealed interface OperationCreationViewState {
    object Idle : OperationCreationViewState
    object Submitting : OperationCreationViewState
    data class Submitted(val message: String) : OperationCreationViewState
    data class Error(val errorMessage: String) : OperationCreationViewState
}

internal class OperationCreationViewModel @Inject constructor(
    private val interactor: CurrencyOperationInteractor
) : ViewModel() {

    private var _viewState: MutableStateFlow<OperationCreationViewState> = MutableStateFlow(
        OperationCreationViewState.Idle
    )
    val viewState: StateFlow<OperationCreationViewState> = _viewState.asStateFlow()

    private val _fromCurrency = MutableStateFlow("")
    val fromCurrency = _fromCurrency.asStateFlow()
    private val _toCurrency = MutableStateFlow("")
    val toCurrency = _toCurrency.asStateFlow()
    private val _amountFrom = MutableStateFlow("")
    val amountFrom = _amountFrom.asStateFlow()
    private val _rate = MutableStateFlow("")
    val rate = _rate.asStateFlow()

    private val _errors = MutableStateFlow(emptySet<FieldError>())
    val errors = _errors.asStateFlow()

    private fun validateInputs(): Boolean {
        _errors.value = buildSet {
            if (fromCurrency.value.isBlank()) {
                add(FieldError.FromCurrencyBlank)
            }
            if (toCurrency.value.isBlank()) {
                add(FieldError.ToCurrencyBlank)
            }
            if (amountFrom.value.isBlank() || (amountFrom.value.toDoubleOrNull() ?: 0.0) <= 0) {
                add(FieldError.AmountFromBlank)
            }

            if (rate.value.isBlank() || (rate.value.toDoubleOrNull() ?: 0.0) <= 0) {
                add(FieldError.RateBlank)
            }
        }

        return errors.value.isEmpty()
    }

    fun submitOperation() {
        if (!validateInputs()) {
            _viewState.value = OperationCreationViewState.Error("Проверьте поля формы")
            return
        }

        viewModelScope.launch {
            _viewState.value = OperationCreationViewState.Submitting

            val operation = CurrencyOperation(
                id = "TEMP_${System.currentTimeMillis()}",
                type = OperationType.CONVERT,
                fromCurrency = fromCurrency.value,
                toCurrency = toCurrency.value,
                amountFrom = amountFrom.value.toDouble(),
                amountTo = amountFrom.value.toDouble() * rate.value.toDouble(),
                rate = rate.value.toDouble(),
                fee = 0.0,
                status = OperationStatus.PENDING,
                timestamp = LocalDateTime.now().toString()
            )

            val result = interactor.submitOperation(operation)
            _viewState.value = result.map(OperationCreationViewState::Submitted)
                .getOrElse {
                    OperationCreationViewState.Error(it.message ?: "Ошибка отправки")
                }
        }
    }

    fun handleError() {
        _viewState.value = OperationCreationViewState.Idle
    }

    fun changeFromCurrency(value: String?) {
        _fromCurrency.value = value.orEmpty()
        _errors.update { errors ->
            errors - FieldError.FromCurrencyBlank
        }
    }

    fun changeToCurrency(value: String?) {
        _toCurrency.value = value.orEmpty()
        _errors.update { errors ->
            errors - FieldError.ToCurrencyBlank
        }
    }

    fun changeAmountFrom(value: String?) {
        _amountFrom.value = value.orEmpty()
        _errors.update { errors ->
            errors - FieldError.AmountFromBlank
        }
    }

    fun changeRate(value: String?) {
        _rate.value = value.orEmpty()
        _errors.update { errors ->
            errors - FieldError.RateBlank
        }
    }

    sealed interface FieldError {
        data object FromCurrencyBlank : FieldError
        data object ToCurrencyBlank : FieldError
        data object AmountFromBlank : FieldError
        data object RateBlank : FieldError
    }
}