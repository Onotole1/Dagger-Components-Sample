package ru.psbank.modules

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class App: DaggerApplication() {
    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<DaggerApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = dispatchingAndroidInjector

    override fun onCreate() {
        DaggerApplicationComponent.create()
            .inject(this)
        super.onCreate()
    }
}
