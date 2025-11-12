package ru.psbank.bookkeeping.di

import dagger.Component
import ru.psbank.bookkeeping.ui.BookkeepingFragment

@Component(modules = [BookkeepingFragmentModule::class])
internal interface BookkeepingFragmentComponent {
    fun inject(fragment: BookkeepingFragment)
}
