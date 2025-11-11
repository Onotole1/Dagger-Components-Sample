package ru.psbank.currencyoperations.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.psbank.currencyoperations.interactor.CurrencyOperationInteractor
import ru.psbank.currencyoperations.repository.CurrencyOperation
import javax.inject.Inject

internal sealed interface OperationsListViewState {
    object Loading : OperationsListViewState
    data class Success(val operations: List<CurrencyOperation>) : OperationsListViewState
    object Error : OperationsListViewState
}

internal class OperationsListViewModel @Inject constructor(
    private val interactor: CurrencyOperationInteractor,
) : ViewModel() {

    private var _viewState: MutableStateFlow<OperationsListViewState> = MutableStateFlow(
        OperationsListViewState.Loading
    )
    val viewState: StateFlow<OperationsListViewState> = _viewState.asStateFlow()

    init {
        loadPayments()
    }

    private fun loadPayments() {
        viewModelScope.launch {
            _viewState.value = OperationsListViewState.Loading
            val result = interactor.loadOperations()
            _viewState.value = result.map(OperationsListViewState::Success)
                .getOrElse { OperationsListViewState.Error }
        }
    }
}
