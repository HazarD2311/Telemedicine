package ru.surfstudio.telemedicine.interactor.auth.network.response

import com.google.gson.annotations.SerializedName
import ru.surfstudio.android.network.Transformable
import ru.surfstudio.telemedicine.domain.auth.recover.RecoverByPhoneStatus
import ru.surfstudio.telemedicine.interactor.common.network.error.InvalidServerValuesResponse

/**
 * ответ от сервера при попытке восстановления доступа
 */
//todo Обновить или удалить класс в соответствии с нуждами приложения
data class RecoverByPhoneResponse(@SerializedName("phone") val phone: String?)
    : Transformable<RecoverByPhoneStatus> {
    override fun transform(): RecoverByPhoneStatus {
        if (phone == null) {
            throw InvalidServerValuesResponse(Pair("phone", "null"))
        }
        return RecoverByPhoneStatus(phone)
    }
}
