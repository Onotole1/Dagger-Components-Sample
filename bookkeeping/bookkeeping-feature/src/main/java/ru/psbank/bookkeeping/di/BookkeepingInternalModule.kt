package ru.psbank.bookkeeping.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.psbank.bookkeeping.BookkeepingStarterImpl
import ru.psbank.bookkeeping.core.BookkeepingStarter

@InstallIn(SingletonComponent::class)
@Module
internal interface BookkeepingInternalModule {
    @Binds
    fun bindBookkeepingStarter(impl: BookkeepingStarterImpl): BookkeepingStarter
}
