package ru.psbank.currencyoperations.interactor

import ru.psbank.currencyoperations.repository.CurrencyOperation
import ru.psbank.currencyoperations.usecase.CreateOperationUseCase
import ru.psbank.currencyoperations.usecase.GetOperationsUseCase
import javax.inject.Inject

internal class CurrencyOperationInteractor @Inject constructor(
    private val getOperationsUseCase: GetOperationsUseCase,
    private val createOperationsUseCase: CreateOperationUseCase,
) {
    suspend fun loadOperations(): Result<List<CurrencyOperation>> = getOperationsUseCase()


    suspend fun submitOperation(operation: CurrencyOperation): Result<String> =
        createOperationsUseCase(operation)
}
