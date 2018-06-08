package ru.surfstudio.telemedicine.interactor.common.network.error


/**
 * Базовый класс ошибки
 */

abstract class BaseMessagedException protected constructor(val developerMessage: String) : RuntimeException()
