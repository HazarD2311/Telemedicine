package ru.surfstudio.telemedicine.ui.screen.splash


import io.reactivex.Completable
import ru.surfstudio.android.core.mvp.presenter.BasePresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.core.ui.navigation.activity.route.ActivityRoute
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.telemedicine.app.intialization.InitializeAppInteractor
import ru.surfstudio.telemedicine.ui.screen.main.MainActivityRoute
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Презентер для сплэш экрана.
 */
@PerScreen
internal class SplashPresenter @Inject
constructor(private val activityNavigator: ActivityNavigator,
            private val initializeAppInteractor: InitializeAppInteractor,
            basePresenterDependency: BasePresenterDependency,
            private val route: SplashRoute)
    : BasePresenter<SplashActivityView>(basePresenterDependency) {

    private val nextRoute: ActivityRoute
        get() {
            return MainActivityRoute()
        }

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)
        if (!viewRecreated) {
            val delay = Completable.timer(TRANSITION_DELAY_MS, TimeUnit.MILLISECONDS)
            val work = initializeAppInteractor.initialize()// полезная работа выполняется в этом Observable
            val merge = Completable.merge(arrayListOf(delay, work))
            subscribeIoHandleError(merge.toObservable<Unit>(), //todo добавить перегрузки на onComplete
                    { },
                    {
                        activityNavigator.finishCurrent()
                        activityNavigator.start(nextRoute)
                    },
                    null)
        }
    }

    companion object {
        /**
         * Минимальное время в миллисекундах в течении которого показывается сплэш
         */
        private val TRANSITION_DELAY_MS = 2000L
    }
}
