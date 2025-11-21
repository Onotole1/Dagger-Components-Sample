package ru.psbank.currencyoperations.di

import dagger.Component
import ru.psbank.currencyoperations.ui.CreateOperationFragment

@Component(dependencies = [CreateOperationFragmentDependencies::class])
internal interface CreateOperationFragmentComponent {
    fun inject(fragment: CreateOperationFragment)
}
