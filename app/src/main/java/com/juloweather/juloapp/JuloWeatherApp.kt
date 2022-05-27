package com.juloweather.juloapp

import com.juloweather.juloapp.base.BaseApplication

class JuloWeatherApp: BaseApplication() {
    override fun getConfigBaseUrl(): String   = BuildConfig.BASE_URL
    override fun getConfigApiKeys(): String   = BuildConfig.X_API_KEY
    override fun isDebugMode(): Boolean       = BuildConfig.DEBUG
}