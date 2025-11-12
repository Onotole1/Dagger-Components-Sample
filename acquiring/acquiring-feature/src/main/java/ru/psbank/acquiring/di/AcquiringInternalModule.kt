package ru.psbank.acquiring.di

import dagger.Binds
import dagger.Module
import ru.psbank.acquiring.AcquiringStarterImpl
import ru.psbank.acquiring.core.AcquiringStarter

@Module
internal interface AcquiringInternalModule {
    @Binds
    fun bindAcquiringStarter(impl: AcquiringStarterImpl): AcquiringStarter
}
