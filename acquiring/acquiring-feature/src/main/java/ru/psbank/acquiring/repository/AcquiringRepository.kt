package ru.psbank.acquiring.repository

import kotlinx.coroutines.delay
import javax.inject.Inject

interface AcquiringRepository {
    suspend fun fetchPayments(): List<Payment>
}

class FakeAcquiringRepository @Inject constructor() : AcquiringRepository {
    override suspend fun fetchPayments(): List<Payment> {
        delay(1200)  // Имитация задержки
        return listOf(
            Payment(
                id = 1,
                amount = 2500.0,
                status = PaymentStatus.SUCCESS,
                timestamp = "2025-11-05 14:30",
                merchant = "Магазин «Продукты»"
            ),
            Payment(
                id = 2,
                amount = 1200.0,
                status = PaymentStatus.FAILED,
                timestamp = "2025-11-04 11:15",
                merchant = "Онлайн-кинотеатр"
            ),
            Payment(
                id = 3,
                amount = 450.0,
                status = PaymentStatus.PENDING,
                timestamp = "2025-11-03 09:45",
                merchant = "Такси"
            )
        )
    }
}

data class Payment(
    val id: Int,
    val amount: Double,
    val status: PaymentStatus,
    val timestamp: String,
    val merchant: String
)

enum class PaymentStatus {
    SUCCESS, FAILED, PENDING
}
