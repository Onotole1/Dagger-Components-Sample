package ru.psbank.acquiringoffice.di

import dagger.Binds
import dagger.Module
import ru.psbank.acquiringoffice.AcquiringOfficeStarterImpl
import ru.psbank.acquiringoffice.core.AcquiringOfficeStarter

@Module
internal interface AcquiringOfficeInternalModule {
    @Binds
    fun bindAcquiringStarter(impl: AcquiringOfficeStarterImpl): AcquiringOfficeStarter
}
