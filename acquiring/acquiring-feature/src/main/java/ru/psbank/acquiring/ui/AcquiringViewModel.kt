package ru.psbank.acquiring.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.psbank.acquiring.interactor.AcquiringInteractor
import ru.psbank.acquiring.repository.Payment
import javax.inject.Inject

internal sealed interface AcquiringViewState {
    object Loading : AcquiringViewState
    data class Success(val payments: List<Payment>) : AcquiringViewState
    object Error : AcquiringViewState
}

@HiltViewModel
internal class AcquiringViewModel @Inject constructor(
    private val interactor: AcquiringInteractor,
) : ViewModel() {

    private var _viewState: MutableStateFlow<AcquiringViewState> = MutableStateFlow(
        AcquiringViewState.Loading
    )
    val viewState: StateFlow<AcquiringViewState> = _viewState.asStateFlow()

    init {
        loadPayments()
    }

    private fun loadPayments() {
        viewModelScope.launch {
            _viewState.value = AcquiringViewState.Loading
            val result = interactor.loadPayments()
            _viewState.value = result.map(AcquiringViewState::Success)
                .getOrElse { AcquiringViewState.Error }
        }
    }
}
