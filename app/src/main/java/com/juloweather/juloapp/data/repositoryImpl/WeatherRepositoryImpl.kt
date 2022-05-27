package com.juloweather.juloapp.data.repositoryImpl

import com.juloweather.juloapp.base.api.ApiResponse
import com.juloweather.juloapp.base.api.safeApiCall
import com.juloweather.juloapp.data.local.db.dao.WeatherDao
import com.juloweather.juloapp.data.remote.WeatherApiService
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val weatherDao: WeatherDao
): WeatherRepository {

    override suspend fun getCurrentWeatherByCityName(cityName: String): ApiResponse<WeatherForecast.WeatherResponse> = safeApiCall {
        apiService.getCurrentWeatherByCityName(cityName)
    }

    override suspend fun getForecast(latitude: Double, longitude: Double, exclude: String): ApiResponse<WeatherForecast.ForecastResponse> = safeApiCall {
        apiService.getForecast(latitude, longitude, exclude)
    }

    override suspend fun getFavoriteCities(): List<WeatherEntities> =
        weatherDao.getFavoriteCities()

    override suspend fun getFavoriteCity(id: Int): WeatherEntities =
        weatherDao.getFavoriteCity(id)

    override suspend fun insertFavoriteCity(weatherEntities: WeatherEntities): Long =
        weatherDao.insert(weatherEntities)

    override suspend fun deleteFavoriteCity(t: WeatherEntities): Int =
        weatherDao.delete(t)


}