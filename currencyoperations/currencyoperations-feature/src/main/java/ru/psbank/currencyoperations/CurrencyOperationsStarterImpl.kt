package ru.psbank.currencyoperations

import androidx.fragment.app.Fragment
import ru.psbank.acquiringoffice.core.CurrencyOperationsStarter
import ru.psbank.currencyoperations.ui.OperationsListFragment
import javax.inject.Inject

internal class CurrencyOperationsStarterImpl @Inject constructor() : CurrencyOperationsStarter {
    override fun createCurrencyOperationsFragment(): Fragment = OperationsListFragment.newInstance()
}
