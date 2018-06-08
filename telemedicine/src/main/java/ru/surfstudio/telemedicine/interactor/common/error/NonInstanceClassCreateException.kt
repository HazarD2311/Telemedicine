package ru.surfstudio.telemedicine.interactor.common.error

/**
 * Ошибка возникающая при попытке инстанцирования класса где это запрещено. (Например Utils классы)
 */

class NonInstanceClassCreateException(clazz: Class<*>) : RuntimeException(clazz.name + " не может иметь инстанс")
