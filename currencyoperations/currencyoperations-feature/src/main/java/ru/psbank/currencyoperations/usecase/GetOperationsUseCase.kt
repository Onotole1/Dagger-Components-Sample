package ru.psbank.currencyoperations.usecase

import ru.psbank.currencyoperations.repository.CurrencyOperation
import ru.psbank.currencyoperations.repository.CurrencyOperationRepository
import javax.inject.Inject

internal class GetOperationsUseCase @Inject constructor(private val repository: CurrencyOperationRepository) {
    suspend operator fun invoke(): Result<List<CurrencyOperation>> = runCatching {
        repository.getOperations()
    }
}
