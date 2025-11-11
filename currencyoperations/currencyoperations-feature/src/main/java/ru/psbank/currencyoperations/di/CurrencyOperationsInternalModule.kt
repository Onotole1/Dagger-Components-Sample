package ru.psbank.currencyoperations.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psbank.acquiringoffice.core.CurrencyOperationsStarter
import ru.psbank.currencyoperations.CurrencyOperationsStarterImpl
import ru.psbank.currencyoperations.repository.CurrencyOperationRepository
import ru.psbank.currencyoperations.repository.FakeCurrencyOperationRepository
import ru.psbank.currencyoperations.ui.CreateOperationFragment
import ru.psbank.currencyoperations.ui.OperationsListFragment
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

    @ContributesAndroidInjector
    fun contributeOperationsListFragment(): OperationsListFragment

    @ContributesAndroidInjector
    fun contributeCreateOperationFragment(): CreateOperationFragment
}
