package com.juloweather.juloapp.base

import android.app.Application

abstract class BaseApplication: Application() {
    abstract fun getConfigBaseUrl(): String
    abstract fun getConfigApiKeys(): String
    abstract fun isDebugMode(): Boolean
}