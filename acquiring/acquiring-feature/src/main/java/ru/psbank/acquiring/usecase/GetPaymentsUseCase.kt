package ru.psbank.acquiring.usecase

import ru.psbank.acquiring.repository.AcquiringRepository
import ru.psbank.acquiring.repository.Payment
import javax.inject.Inject

internal class GetPaymentsUseCase @Inject constructor(private val repository: AcquiringRepository) {
    suspend operator fun invoke(): Result<List<Payment>> = runCatching {
        repository.fetchPayments()
    }
}
