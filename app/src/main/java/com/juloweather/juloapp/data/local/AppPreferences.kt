package com.juloweather.juloapp.data.local

import com.juloweather.juloapp.domain.entitites.WeatherEntities

interface AppPreferences {
    fun setIsFirstLaunch(firstLaunch: Boolean)
    fun isFirstLaunch(): Boolean
    fun setSelectedLat(lat: Double)
    fun setSelectedCityLon(lon: Double)
    fun getSelectedLat(): Double
    fun getSelectedCityLon(): Double
    fun setSelectedCityName(cityName: String)
    fun getSelectedCityName(): String
}