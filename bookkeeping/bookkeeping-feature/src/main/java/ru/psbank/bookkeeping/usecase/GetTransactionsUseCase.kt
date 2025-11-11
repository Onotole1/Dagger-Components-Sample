package ru.psbank.bookkeeping.usecase

import ru.psbank.bookkeeping.repository.BookkeepingRepository
import ru.psbank.bookkeeping.repository.Transaction
import javax.inject.Inject

internal class GetTransactionsUseCase @Inject constructor (private val repository: BookkeepingRepository) {
    suspend operator fun invoke(): Result<List<Transaction>> = runCatching {
        repository.fetchTransactions()
    }
}
