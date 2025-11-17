package ru.psbank.acquiringoffice.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.psbank.acquiringoffice.core.AcquiringOfficeStarter
import ru.psbank.acquiringoffice.AcquiringOfficeStarterImpl

@InstallIn(SingletonComponent::class)
@Module
internal interface AcquiringOfficeInternalModule {
    @Binds
    fun bindAcquiringStarter(impl: AcquiringOfficeStarterImpl): AcquiringOfficeStarter
}
