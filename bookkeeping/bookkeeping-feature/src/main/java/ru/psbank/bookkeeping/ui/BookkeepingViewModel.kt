package ru.psbank.bookkeeping.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.psbank.bookkeeping.interactor.BookkeepingInteractor
import ru.psbank.bookkeeping.repository.Transaction
import javax.inject.Inject

internal sealed interface BookkeepingViewState {
    object Loading : BookkeepingViewState
    data class Success(val transactions: List<Transaction>) : BookkeepingViewState
    object Error : BookkeepingViewState
}

@HiltViewModel
internal class BookkeepingViewModel @Inject constructor(
    private val interactor: BookkeepingInteractor,
) : ViewModel() {

    private var _viewState: MutableStateFlow<BookkeepingViewState> = MutableStateFlow(
        BookkeepingViewState.Loading
    )
    val viewState: StateFlow<BookkeepingViewState> = _viewState.asStateFlow()

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            _viewState.value = BookkeepingViewState.Loading
            val result = interactor.loadTransactions()
            _viewState.value = result.map(BookkeepingViewState::Success)
                .getOrElse { BookkeepingViewState.Error }
        }
    }
}
