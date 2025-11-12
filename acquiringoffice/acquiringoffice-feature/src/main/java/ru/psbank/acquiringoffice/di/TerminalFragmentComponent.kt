package ru.psbank.acquiringoffice.di

import dagger.Component
import ru.psbank.acquiringoffice.ui.TerminalFragment

@Component(modules = [TerminalFragmentModule::class])
internal interface TerminalFragmentComponent {
    fun inject(fragment: TerminalFragment)
}
