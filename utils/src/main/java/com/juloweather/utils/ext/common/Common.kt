package com.juloweather.utils.ext.common

import android.os.Handler
import android.os.Looper

inline fun <T> T.applyIf(predicate: Boolean, block: T.() -> Unit): T = apply {
    if (predicate) block(this)
}

fun launchDelayedFunction(timeMillis: Long = 3000, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action() }, timeMillis)
}

fun forceClose() {
    //android.oslaunchDelayedFunction.Process.killProcess(android.os.Process.myPid())
}