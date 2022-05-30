package com.juloweather.juloapp.data.local.prefs

import android.app.Application
import android.content.Context
import com.juloweather.utils.qualifier.PreferenceInfo
import javax.inject.Inject

class PreferenceImpl @Inject constructor(app: Application, @PreferenceInfo private val prefName: String): AppPrefs {

    companion object{
        const val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"
    }

    private val prefs = app.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    override fun setIsFirstTimeLaunch(t: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, t)
        editor.apply()
    }

    override fun isFirstTimeLaunch(): Boolean = prefs.getBoolean(IS_FIRST_TIME_LAUNCH, true) ?: true


}