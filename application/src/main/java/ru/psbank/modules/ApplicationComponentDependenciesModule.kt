package ru.psbank.modules

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.psbank.currencyoperations.di.CreateOperationFragmentDependencies
import ru.psbank.currencyoperations.di.OperationsListFragmentDependencies
import ru.psbank.utls.ComponentDependencies
import ru.psbank.utls.ComponentDependenciesKey

/**
 * Связывает ApplicationComponent с конкретными реализациями Dependencies
 */
@Module
internal interface ApplicationComponentDependenciesModule {
    @Binds
    @IntoMap
    @ComponentDependenciesKey(CreateOperationFragmentDependencies::class)
    fun bindCreateOperationFragmentDependencies(impl: ApplicationComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(OperationsListFragmentDependencies::class)
    fun bindOperationsListFragmentDependencies(impl: ApplicationComponent): ComponentDependencies
}
