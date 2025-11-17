package ru.psbank.acquiring.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.psbank.acquiring.AcquiringStarterImpl
import ru.psbank.acquiring.core.AcquiringStarter

@InstallIn(SingletonComponent::class)
@Module
internal interface AcquiringInternalModule {
    @Binds
    fun bindAcquiringStarter(impl: AcquiringStarterImpl): AcquiringStarter
}
