package ru.psbank.currencyoperations.usecase

import ru.psbank.currencyoperations.repository.CurrencyOperation
import ru.psbank.currencyoperations.repository.CurrencyOperationRepository
import javax.inject.Inject

class CreateOperationUseCase @Inject constructor (private val repository: CurrencyOperationRepository) {
    suspend operator fun invoke(operation: CurrencyOperation): Result<String> {
        return repository.createOperation(operation)
    }
}
