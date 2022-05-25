package com.juloweather.utils.ext.debug

import com.juloweather.utils.BuildConfig

fun debug(action: () -> Unit) {
    if (BuildConfig.DEBUG) {
        action.invoke()
    }
}