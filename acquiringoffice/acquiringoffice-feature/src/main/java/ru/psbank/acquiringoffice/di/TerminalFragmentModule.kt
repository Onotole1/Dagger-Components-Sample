package ru.psbank.acquiringoffice.di

import dagger.Binds
import dagger.Module
import ru.psbank.acquiringoffice.repository.FakeTerminalRepository
import ru.psbank.acquiringoffice.repository.TerminalRepository

@Module
internal interface TerminalFragmentModule {
    @Binds
    fun bindTerminalRepository(impl: FakeTerminalRepository): TerminalRepository
}
