package ru.psbank.bookkeeping.interactor

import ru.psbank.bookkeeping.repository.Transaction
import ru.psbank.bookkeeping.usecase.GetTransactionsUseCase
import javax.inject.Inject

internal class BookkeepingInteractor @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase
) {
    suspend fun loadTransactions(): Result<List<Transaction>> = getTransactionsUseCase()
}
