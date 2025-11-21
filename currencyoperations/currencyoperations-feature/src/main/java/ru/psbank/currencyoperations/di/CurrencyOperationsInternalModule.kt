package ru.psbank.currencyoperations.di

import dagger.Binds
import dagger.Module
import ru.psbank.acquiringoffice.core.CurrencyOperationsStarter
import ru.psbank.currencyoperations.CurrencyOperationsStarterImpl
import ru.psbank.currencyoperations.repository.CurrencyOperationRepository
import ru.psbank.currencyoperations.repository.FakeCurrencyOperationRepository
import javax.inject.Singleton

@Module
internal interface CurrencyOperationsInternalModule {
    @Binds
    fun bindCurrencyOperationsStarter(impl: CurrencyOperationsStarterImpl): CurrencyOperationsStarter

    @Singleton
    @Binds
    fun bindCurrencyOperationRepository(
        impl: FakeCurrencyOperationRepository,
    ): CurrencyOperationRepository
}
