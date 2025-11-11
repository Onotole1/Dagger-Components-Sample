package ru.psbank.bookkeeping.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psbank.bookkeeping.BookkeepingStarterImpl
import ru.psbank.bookkeeping.core.BookkeepingStarter
import ru.psbank.bookkeeping.ui.BookkeepingFragment

@Module
internal interface BookkeepingInternalModule {
    @Binds
    fun bindBookkeepingStarter(impl: BookkeepingStarterImpl): BookkeepingStarter

    @ContributesAndroidInjector(modules = [BookkeepingFragmentModule::class])
    fun contributeBookkeepingFragment(): BookkeepingFragment
}
