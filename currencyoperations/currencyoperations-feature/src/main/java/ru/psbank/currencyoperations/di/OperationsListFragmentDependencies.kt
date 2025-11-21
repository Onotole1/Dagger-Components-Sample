package ru.psbank.currencyoperations.di

import ru.psbank.currencyoperations.repository.CurrencyOperationRepository
import ru.psbank.utls.ComponentDependencies

interface OperationsListFragmentDependencies : ComponentDependencies {
    val currencyOperationRepository: CurrencyOperationRepository
}
