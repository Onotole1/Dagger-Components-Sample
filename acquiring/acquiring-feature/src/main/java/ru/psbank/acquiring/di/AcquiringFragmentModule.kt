package ru.psbank.acquiring.di

import dagger.Binds
import dagger.Module
import ru.psbank.acquiring.repository.AcquiringRepository
import ru.psbank.acquiring.repository.FakeAcquiringRepository

@Module
internal interface AcquiringFragmentModule {
    @Binds
    fun bindAcquiringRepository(impl: FakeAcquiringRepository): AcquiringRepository
}
