package ru.surfstudio.telemedicine.app.intialization.migration

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.app.migration.AppLaunchConfigurationStorage
import ru.surfstudio.android.app.migration.AppMigrationManager
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.shared.pref.NO_BACKUP_SHARED_PREF
import ru.surfstudio.telemedicine.BuildConfig
import javax.inject.Named

/**
 * Модуль ддля предоставления зависимостей для миграции приложения
 */
@Module
class MigrationModule {

    @PerApplication
    @Provides
    fun provideAppLaunchConfigurationStorage(@Named(NO_BACKUP_SHARED_PREF) sharedPreferences: SharedPreferences): AppLaunchConfigurationStorage {
        return AppLaunchConfigurationStorage(sharedPreferences)
    }

    @PerApplication
    @Provides
    fun provideAppMigrationManager(appLaunchConfigurationStorage: AppLaunchConfigurationStorage): AppMigrationManager {
        return AppMigrationManager(AppMigrationStorageImpl(), appLaunchConfigurationStorage, BuildConfig.VERSION_CODE)
    }
}