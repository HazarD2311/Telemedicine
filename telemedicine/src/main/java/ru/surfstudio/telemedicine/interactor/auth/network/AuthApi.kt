package ru.surfstudio.telemedicine.interactor.auth.network

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.surfstudio.telemedicine.domain.auth.phone.LoginInfo
import ru.surfstudio.telemedicine.interactor.auth.network.request.*
import ru.surfstudio.telemedicine.interactor.auth.network.response.KeyResponse
import ru.surfstudio.telemedicine.interactor.auth.network.response.RecoverByEmailResponse
import ru.surfstudio.telemedicine.interactor.auth.network.response.RecoverByPhoneResponse
import ru.surfstudio.telemedicine.interactor.auth.network.response.TokenResponse

/**
 * Api для авторизации
 */
interface AuthApi {

    @POST(LOGIN_BY_PHONE_PATH)
    fun requestCode(@Body phoneRequest: LoginByPhoneRequest): Observable<KeyResponse>

    @FormUrlEncoded
    @POST(GET_TOKEN_PATH)
    fun loginByCode(@Body request: LoginByCodeRequest): Observable<TokenResponse>

    @POST(LOGIN_BY_EMAIL_PATH)
    fun loginByEmail(@Body phoneRequest: LoginByEmailRequest): Observable<LoginInfo>

    @POST(USER_LOGOUT_PATH)
    fun logout(): Observable<Unit>

    @POST(RECOVER_ACCESS_BY_EMAIL_PATH)
    fun recoverByEmail(@Body request: RecoverByEmailRequest): Observable<RecoverByEmailResponse>

    @POST(RECOVER_ACCESS_BY_PHONE_PATH)
    fun recoverByPhone(@Body request: RecoverByPhoneRequest): Observable<RecoverByPhoneResponse>
}