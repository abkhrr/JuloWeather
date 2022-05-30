package com.juloweather.juloapp.features.cities.adapter.click

import com.juloweather.juloapp.domain.entitites.WeatherEntities

interface OnClickListener {
    fun adapterFavClicked(item: WeatherEntities)
    fun adapterSelectedCIty(lat: Double, lon: Double, cityName: String)
}