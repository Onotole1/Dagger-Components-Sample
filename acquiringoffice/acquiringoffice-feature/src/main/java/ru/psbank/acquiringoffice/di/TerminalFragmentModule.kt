package ru.psbank.acquiringoffice.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.psbank.acquiringoffice.repository.FakeTerminalRepository
import ru.psbank.acquiringoffice.repository.TerminalRepository

@InstallIn(ViewModelComponent::class)
@Module
internal interface TerminalFragmentModule {
    @Binds
    fun bindTerminalRepository(impl: FakeTerminalRepository): TerminalRepository
}
