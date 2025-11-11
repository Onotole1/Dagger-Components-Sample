package ru.psbank.bookkeeping.di

import dagger.Binds
import dagger.Module
import ru.psbank.bookkeeping.repository.BookkeepingRepository
import ru.psbank.bookkeeping.repository.FakeBookkeepingRepository

@Module
internal interface BookkeepingFragmentModule {
    @Binds
    fun bindBookkeepingRepository(impl: FakeBookkeepingRepository): BookkeepingRepository
}