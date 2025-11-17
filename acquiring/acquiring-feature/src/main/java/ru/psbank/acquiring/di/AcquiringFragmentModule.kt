package ru.psbank.acquiring.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.psbank.acquiring.repository.AcquiringRepository
import ru.psbank.acquiring.repository.FakeAcquiringRepository

@InstallIn(ViewModelComponent::class)
@Module
internal interface AcquiringFragmentModule {
    @Binds
    fun bindAcquiringRepository(impl: FakeAcquiringRepository): AcquiringRepository
}