package ru.surfstudio.telemedicine.interactor.common.network.response


import com.google.gson.annotations.SerializedName


data class AuthErrorObj(@SerializedName("error")
                        val error: String? = null) : BaseResponse
