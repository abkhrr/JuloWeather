package com.juloweather.juloapp.data.local.prefs

interface AppPrefs {
    fun setIsFirstTimeLaunch(t: Boolean)
    fun isFirstTimeLaunch(): Boolean
}