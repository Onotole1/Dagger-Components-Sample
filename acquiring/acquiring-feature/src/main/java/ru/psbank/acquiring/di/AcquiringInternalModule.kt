package ru.psbank.acquiring.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psbank.acquiring.AcquiringStarterImpl
import ru.psbank.acquiring.core.AcquiringStarter
import ru.psbank.acquiring.ui.AcquiringFragment

@Module
internal interface AcquiringInternalModule {
    @Binds
    fun bindAcquiringStarter(impl: AcquiringStarterImpl): AcquiringStarter

    @ContributesAndroidInjector(modules = [AcquiringFragmentModule::class])
    fun contributeAcquiringFragment(): AcquiringFragment
}
