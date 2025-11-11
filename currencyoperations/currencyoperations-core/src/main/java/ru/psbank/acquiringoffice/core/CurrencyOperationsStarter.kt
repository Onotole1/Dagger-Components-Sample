package ru.psbank.acquiringoffice.core

import androidx.fragment.app.Fragment

interface CurrencyOperationsStarter {
    fun createCurrencyOperationsFragment(): Fragment
}