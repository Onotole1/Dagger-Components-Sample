package ru.psbank.currencyoperations.repository

import kotlinx.coroutines.delay
import javax.inject.Inject

interface CurrencyOperationRepository {
    suspend fun getOperations(): List<CurrencyOperation>
    suspend fun createOperation(operation: CurrencyOperation): Result<String>
}

class FakeCurrencyOperationRepository @Inject constructor() : CurrencyOperationRepository {
    private val operations = mutableListOf(
        CurrencyOperation(
            id = "OP001",
            type = OperationType.BUY,
            fromCurrency = "USD",
            toCurrency = "RUB",
            amountFrom = 100.0,
            amountTo = 9250.0,
            rate = 92.50,
            fee = 50.0,
            status = OperationStatus.COMPLETED,
            timestamp = "06.11.2025 10:30"
        ),
        CurrencyOperation(
            id = "OP002",
            type = OperationType.CONVERT,
            fromCurrency = "EUR",
            toCurrency = "USD",
            amountFrom = 200.0,
            amountTo = 218.0,
            rate = 1.09,
            fee = 10.0,
            status = OperationStatus.PENDING,
            timestamp = "06.11.2025 11:45"
        )
    )

    override suspend fun getOperations(): List<CurrencyOperation> {
        delay(800)
        return operations
    }

    override suspend fun createOperation(operation: CurrencyOperation): Result<String> {
        operations.add(operation)
        return Result.success("Операция ${operation.id} создана")
    }
}


enum class OperationType {
    BUY, SELL, CONVERT
}

enum class OperationStatus {
    PENDING, COMPLETED, FAILED
}

data class CurrencyOperation(
    val id: String,
    val type: OperationType,
    val fromCurrency: String,  // напр., "USD"
    val toCurrency: String,    // напр., "RUB"
    val amountFrom: Double,
    val amountTo: Double,
    val rate: Double,           // курс обмена
    val fee: Double,          // комиссия
    val status: OperationStatus,
    val timestamp: String        // дата и время
)
