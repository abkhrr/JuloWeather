package com.juloweather.juloapp.data.local

import android.app.Application
import android.content.Context
import com.juloweather.utils.ext.prefs.getDouble
import com.juloweather.utils.ext.prefs.putDouble
import com.juloweather.utils.qualifier.PreferenceInfo
import javax.inject.Inject

class PreferenceImpl @Inject constructor(app: Application, @PreferenceInfo private val prefName: String): AppPreferences {

    companion object{
        const val PREF_KEY_IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH"
        const val PREF_KEY_CITY_LAT        = "CITY_LATITUDE"
        const val PREF_KEY_CITY_LON        = "CITY_LONGITUDE"
        const val PREF_KEY_CITY_NAME       = "CITY_NAME"
    }

    private val prefs = app.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    override fun setIsFirstLaunch(firstLaunch: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(PREF_KEY_IS_FIRST_LAUNCH, firstLaunch)
        editor.apply()
    }

    override fun setSelectedLat(lat: Double) {
        val editor = prefs.edit()
        editor.putDouble(PREF_KEY_CITY_LAT, lat)
        editor.apply()
    }

    override fun setSelectedCityLon(lon: Double) {
        val editor = prefs.edit()
        editor.putDouble(PREF_KEY_CITY_LON, lon)
        editor.apply()
    }

    override fun setSelectedCityName(cityName: String) {
        val editor = prefs.edit()
        editor.putString(PREF_KEY_CITY_NAME, cityName)
        editor.apply()
    }

    override fun isFirstLaunch(): Boolean      = prefs.getBoolean(PREF_KEY_IS_FIRST_LAUNCH, true)
    override fun getSelectedLat(): Double      = prefs.getDouble(PREF_KEY_CITY_LAT, 0.0)
    override fun getSelectedCityLon(): Double  = prefs.getDouble(PREF_KEY_CITY_LON, 0.0)
    override fun getSelectedCityName(): String = prefs.getString(PREF_KEY_CITY_NAME, "") ?: ""

}