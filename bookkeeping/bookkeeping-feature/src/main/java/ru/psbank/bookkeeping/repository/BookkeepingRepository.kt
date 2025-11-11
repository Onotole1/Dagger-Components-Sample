package ru.psbank.bookkeeping.repository

import kotlinx.coroutines.delay
import javax.inject.Inject

internal interface BookkeepingRepository {
    suspend fun fetchTransactions(): List<Transaction>
}

internal class FakeBookkeepingRepository @Inject constructor() : BookkeepingRepository {
    override suspend fun fetchTransactions(): List<Transaction> {
        // Имитируем задержку сети/базы
        delay(1500)
        return listOf(
            Transaction(id = 1, amount = 1000.0, date = "2025-10-01", description = "Зарплата"),
            Transaction(id = 2, amount = -500.0, date = "2025-10-05", description = "Аренда"),
            Transaction(id = 3, amount = -200.0, date = "2025-10-10", description = "Коммуналка")
        )
    }
}

internal data class Transaction(
    val id: Int,
    val amount: Double,
    val date: String,
    val description: String
)
