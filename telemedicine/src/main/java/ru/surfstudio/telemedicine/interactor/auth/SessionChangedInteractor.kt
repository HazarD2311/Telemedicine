package ru.surfstudio.telemedicine.interactor.auth

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.telemedicine.domain.auth.phone.LoginInfo
import javax.inject.Inject

/**
 * инкапсулирует действия, которые необходимо выполнить при смене сессии/пользователя
 */
@PerApplication
class SessionChangedInteractor
@Inject
constructor(private val tokenStorage: TokenStorage) {
    private val sessionChangedPublishSubject = PublishSubject.create<LoginState>()

    fun observeSessionChanged(): Observable<LoginState> {
        return sessionChangedPublishSubject
    }

    fun onLogin(authInfo: LoginInfo) {
        tokenStorage.authToken = authInfo.accessToken //todo log user
        sessionChangedPublishSubject.onNext(LoginState.LOGGED_IN)
    }

    fun onForceLogout() {
        tokenStorage.clearTokens()
        sessionChangedPublishSubject.onNext(LoginState.NOT_AUTHORIZED) //todo log user
    }
}