package ru.psbank.modules

import dagger.Component
import ru.psbank.acquiring.di.AcquiringModule
import ru.psbank.acquiringoffice.di.AcquiringOfficeModule
import ru.psbank.bookkeeping.di.BookkeepingModule
import ru.psbank.currencyoperations.di.CurrencyOperationsModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AcquiringModule::class,
        BookkeepingModule::class,
        AcquiringOfficeModule::class,
        CurrencyOperationsModule::class,
        ApplicationComponentDependenciesModule::class,
    ]
)
internal interface ApplicationComponent: ApplicationComponentDependencies {
    fun inject(app: App)
}
