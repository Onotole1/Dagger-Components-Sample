package ru.psbank.currencyoperations.di

import dagger.Component
import ru.psbank.currencyoperations.ui.OperationsListFragment

@Component(dependencies = [OperationsListFragmentDependencies::class])
internal interface OperationsListFragmentComponent {
    fun inject(fragment: OperationsListFragment)
}
