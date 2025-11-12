package ru.psbank.bookkeeping.di

import dagger.Binds
import dagger.Module
import ru.psbank.bookkeeping.BookkeepingStarterImpl
import ru.psbank.bookkeeping.core.BookkeepingStarter

@Module
internal interface BookkeepingInternalModule {
    @Binds
    fun bindBookkeepingStarter(impl: BookkeepingStarterImpl): BookkeepingStarter
}
