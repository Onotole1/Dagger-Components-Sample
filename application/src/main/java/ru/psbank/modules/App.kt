package ru.psbank.modules

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import ru.psbank.utls.ComponentDependenciesProvider
import ru.psbank.utls.HasComponentDependencies
import javax.inject.Inject

class App: DaggerApplication(), HasComponentDependencies {
    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<DaggerApplication>

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = dispatchingAndroidInjector

    override fun onCreate() {
        DaggerApplicationComponent.create()
            .inject(this)
        super.onCreate()
    }
}
