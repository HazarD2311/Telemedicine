package ru.surfstudio.telemedicine.app.dagger

import android.content.Context
import dagger.Component
import ru.surfstudio.android.connection.ConnectionProvider
import ru.surfstudio.android.core.app.ActiveActivityHolder
import ru.surfstudio.android.core.app.StringsProvider
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProvider
import ru.surfstudio.telemedicine.app.intialization.InitializeAppInteractor
import ru.surfstudio.telemedicine.app.intialization.migration.MigrationModule
import ru.surfstudio.telemedicine.interactor.analytics.AnalyticsModule
import ru.surfstudio.telemedicine.interactor.analytics.AnalyticsService
import ru.surfstudio.telemedicine.interactor.auth.AuthModule
import ru.surfstudio.telemedicine.interactor.auth.SessionChangedInteractor
import ru.surfstudio.telemedicine.interactor.common.network.NetworkModule
import ru.surfstudio.telemedicine.interactor.common.network.OkHttpModule

@PerApplication
@Component(modules = [
    AppModule::class,
    MigrationModule::class,
    SharedPrefModule::class,
    AuthModule::class,
    NetworkModule::class,
    OkHttpModule::class,
    AnalyticsModule::class])
interface AppComponent {
    fun initializeAppInteractor(): InitializeAppInteractor
    fun context(): Context
    fun activeActivityHolder(): ActiveActivityHolder
    fun connectionProvider(): ConnectionProvider
    fun sessionChangeInteractor(): SessionChangedInteractor
    fun analyticsService(): AnalyticsService
    fun schedulerProvider(): SchedulersProvider
    fun stringsProvider(): StringsProvider
}
