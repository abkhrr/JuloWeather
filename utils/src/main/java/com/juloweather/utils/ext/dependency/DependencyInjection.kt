package com.juloweather.utils.ext.dependency

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun <T>getValueLazyOf(instanceState: MutableStateFlow<T>): Lazy<T> {
    val immutable: StateFlow<T> = instanceState
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        immutable.value
    }
}

inline fun <reified T: Any?>getValueOf(instanceState: MutableStateFlow<T?>): T {
    val immutable: StateFlow<T?> = instanceState
    val className = T::class.simpleName
    return try {
        immutable.value!!
    } catch (e: ExceptionInInitializerError) {
        throw e
    }
}

inline fun <reified T>getValueSafeOf(instanceState: MutableStateFlow<T>): T {
    val immutable: StateFlow<T> = instanceState
    return immutable.value
}