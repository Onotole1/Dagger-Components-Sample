package ru.psbank.acquiringoffice.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psbank.acquiringoffice.core.AcquiringOfficeStarter
import ru.psbank.acquiringoffice.AcquiringOfficeStarterImpl
import ru.psbank.acquiringoffice.ui.TerminalFragment

@Module
internal interface AcquiringOfficeInternalModule {
    @Binds
    fun bindAcquiringStarter(impl: AcquiringOfficeStarterImpl): AcquiringOfficeStarter

    @ContributesAndroidInjector(modules = [TerminalFragmentModule::class])
    fun contributeTerminalFragment(): TerminalFragment
}
