package ru.psbank.mainscreen.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psbank.mainscreen.ui.MainScreenFragment

@Module
internal interface MainScreenInternalModule {
    @ContributesAndroidInjector
    fun contributeMainScreenFragment(): MainScreenFragment
}