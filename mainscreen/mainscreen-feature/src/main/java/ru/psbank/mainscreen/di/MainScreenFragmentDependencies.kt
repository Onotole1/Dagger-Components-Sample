package ru.psbank.mainscreen.di

import ru.psbank.acquiring.core.AcquiringStarter
import ru.psbank.acquiringoffice.core.AcquiringOfficeStarter
import ru.psbank.acquiringoffice.core.CurrencyOperationsStarter
import ru.psbank.bookkeeping.core.BookkeepingStarter
import ru.psbank.utls.ComponentDependencies

interface MainScreenFragmentDependencies: ComponentDependencies {
    val acquiringOfficeStarter: AcquiringOfficeStarter
    val acquiringStarter: AcquiringStarter
    val bookkeepingStarter: BookkeepingStarter
    val currencyOperationsStarter: CurrencyOperationsStarter
}
