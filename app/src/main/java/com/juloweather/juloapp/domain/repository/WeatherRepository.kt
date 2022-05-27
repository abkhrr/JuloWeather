package com.juloweather.juloapp.domain.repository

import com.juloweather.juloapp.base.api.ApiResponse
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.domain.model.WeatherForecast

interface WeatherRepository {

    suspend fun getCurrentWeatherByCityName(
        cityName: String
    ): ApiResponse<WeatherForecast.WeatherResponse>

    suspend fun getForecast(
        latitude: Double,
        longitude: Double,
        exclude: String
    ): ApiResponse<WeatherForecast.ForecastResponse>

    suspend fun getFavoriteCities(): List<WeatherEntities>
    suspend fun getFavoriteCity(id: Int): WeatherEntities
    suspend fun insertFavoriteCity(weatherEntities: WeatherEntities): Long
    suspend fun deleteFavoriteCity(t: WeatherEntities): Int
}