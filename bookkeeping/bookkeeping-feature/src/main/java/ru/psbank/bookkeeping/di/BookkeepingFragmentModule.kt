package ru.psbank.bookkeeping.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.psbank.bookkeeping.repository.BookkeepingRepository
import ru.psbank.bookkeeping.repository.FakeBookkeepingRepository

@InstallIn(ViewModelComponent::class)
@Module
internal interface BookkeepingFragmentModule {
    @Binds
    fun bindBookkeepingRepository(impl: FakeBookkeepingRepository): BookkeepingRepository
}
