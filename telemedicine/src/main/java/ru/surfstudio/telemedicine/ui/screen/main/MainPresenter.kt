package ru.surfstudio.telemedicine.ui.screen.main

import ru.surfstudio.android.core.mvp.presenter.BasePresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.telemedicine.interactor.analytics.AnalyticsService
import ru.surfstudio.telemedicine.interactor.analytics.event.EnterEvent
import javax.inject.Inject

/**
 * Презентер главного экрана
 */
@PerScreen
internal class MainPresenter @Inject constructor(private val analyticsService: AnalyticsService,
                                                 basePresenterDependency: BasePresenterDependency)
    : BasePresenter<MainActivityView>(basePresenterDependency) {

    private val screenModel: MainScreenModel = MainScreenModel()

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)
        view.render(screenModel)
        analyticsService.sendEvent(EnterEvent())
    }
}