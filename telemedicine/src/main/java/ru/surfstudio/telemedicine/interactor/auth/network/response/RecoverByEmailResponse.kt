package ru.surfstudio.telemedicine.interactor.auth.network.response

import com.google.gson.annotations.SerializedName
import ru.surfstudio.android.network.Transformable
import ru.surfstudio.telemedicine.domain.auth.recover.RecoverByEmailStatus
import ru.surfstudio.telemedicine.interactor.common.network.error.InvalidServerValuesResponse

/**
 * ответ от сервера при попытке восстановления доступа через почту
 */
//todo Обновить или удалить класс в соответствии с нуждами приложения
data class RecoverByEmailResponse(@SerializedName("email") private val email: String?) : Transformable<RecoverByEmailStatus> {
    override fun transform(): RecoverByEmailStatus {
        if (email == null) {
            throw InvalidServerValuesResponse(Pair("email", "null"))
        }
        return RecoverByEmailStatus(email)
    }
}