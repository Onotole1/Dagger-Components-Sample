package ru.psbank.mainscreen.di

import dagger.Component
import ru.psbank.mainscreen.ui.MainScreenFragment

@Component(dependencies = [MainScreenFragmentDependencies::class])
internal interface MainScreenFragmentComponent {
    fun inject(fragment: MainScreenFragment)
}
