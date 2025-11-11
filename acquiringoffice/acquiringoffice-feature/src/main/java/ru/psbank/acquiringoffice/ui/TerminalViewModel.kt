package ru.psbank.acquiringoffice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.psbank.acquiringoffice.interactor.TerminalInteractor
import ru.psbank.acquiringoffice.repository.Terminal
import javax.inject.Inject

internal sealed interface TerminalViewState {
    object Loading : TerminalViewState
    data class Success(val payments: List<Terminal>) : TerminalViewState
    object Error : TerminalViewState
}

internal class TerminalViewModel @Inject constructor(
    private val interactor: TerminalInteractor,
) : ViewModel() {

    private var _viewState: MutableStateFlow<TerminalViewState> = MutableStateFlow(
        TerminalViewState.Loading
    )
    val viewState: StateFlow<TerminalViewState> = _viewState.asStateFlow()

    init {
        loadPayments()
    }

    private fun loadPayments() {
        viewModelScope.launch {
            _viewState.value = TerminalViewState.Loading
            val result = interactor.loadTerminals()
            _viewState.value = result.map(TerminalViewState::Success)
                .getOrElse { TerminalViewState.Error }
        }
    }
}
