package ru.surfstudio.telemedicine.ui.screen.splash

import android.support.annotation.LayoutRes
import ru.surfstudio.android.core.mvp.activity.CoreActivityView
import ru.surfstudio.android.core.mvp.presenter.CorePresenter
import ru.surfstudio.telemedicine.R
import ru.surfstudio.telemedicine.ui.base.configurator.ActivityScreenConfigurator
import javax.inject.Inject

/**
 * Сплэш активити.
 */
class SplashActivityView : CoreActivityView() {
    @Inject internal lateinit var presenter: SplashPresenter

    @LayoutRes
    override fun getContentView(): Int {
        return R.layout.activity_splash
    }

    override fun getPresenters(): Array<CorePresenter<*>> {
        return arrayOf(presenter)
    }

    override fun createConfigurator(): ActivityScreenConfigurator {
        return SplashScreenConfigurator(intent)
    }

    override fun getScreenName(): String {
        return "splash"
    }

}
