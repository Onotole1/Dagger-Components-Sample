package ru.psbank.acquiring.di

import dagger.Component
import ru.psbank.acquiring.ui.AcquiringFragment

@Component(modules = [AcquiringFragmentModule::class])
internal interface AcquiringFragmentComponent {
    fun inject(fragment: AcquiringFragment)
}
