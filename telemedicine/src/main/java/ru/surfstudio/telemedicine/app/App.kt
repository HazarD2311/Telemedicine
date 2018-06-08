package ru.surfstudio.telemedicine.app

import ru.surfstudio.android.core.app.CoreApp
import ru.surfstudio.telemedicine.app.dagger.AppComponent
import ru.surfstudio.telemedicine.app.dagger.AppModule
import ru.surfstudio.telemedicine.app.dagger.DaggerAppComponent

/**
 * Класс приложения
 */
class App : CoreApp() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        initInjector()
    }

    private fun initInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}