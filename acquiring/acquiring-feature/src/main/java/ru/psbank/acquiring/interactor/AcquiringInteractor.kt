package ru.psbank.acquiring.interactor

import ru.psbank.acquiring.repository.Payment
import ru.psbank.acquiring.usecase.GetPaymentsUseCase
import javax.inject.Inject

internal class AcquiringInteractor @Inject constructor(
    private val getPaymentsUseCase: GetPaymentsUseCase,
) {
    suspend fun loadPayments(): Result<List<Payment>> = getPaymentsUseCase()
}
