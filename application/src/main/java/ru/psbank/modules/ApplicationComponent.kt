package ru.psbank.modules

import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.psbank.acquiring.di.AcquiringModule
import ru.psbank.acquiringoffice.di.AcquiringOfficeModule
import ru.psbank.bookkeeping.di.BookkeepingModule
import ru.psbank.currencyoperations.di.CurrencyOperationsModule
import ru.psbank.mainscreen.di.MainScreenModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AcquiringModule::class,
        MainScreenModule::class,
        BookkeepingModule::class,
        AcquiringOfficeModule::class,
        CurrencyOperationsModule::class,
        AndroidInjectionModule::class,
    ]
)
interface ApplicationComponent {
    fun inject(app: App)
}
