package ru.psbank.modules

import android.app.Application
import ru.psbank.utls.ComponentDependenciesProvider
import ru.psbank.utls.HasComponentDependencies
import javax.inject.Inject

class App: Application(), HasComponentDependencies {
    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        DaggerApplicationComponent.create()
            .inject(this)
        super.onCreate()
    }
}
