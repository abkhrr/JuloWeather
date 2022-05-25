package com.juloweather.juloapp.data.remote

import com.juloweather.juloapp.base.api.ApiEndPoint
import com.juloweather.juloapp.domain.model.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {

    @GET(ApiEndPoint.GET_WEATHER_DATA)
    suspend fun getCurrentWeatherByCityName(
        @Query("q") cityName: String,
    ): WeatherForecast.WeatherResponse

    @GET(ApiEndPoint.GET_FORECAST_DATA)
    suspend fun getForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("exclude") exclude: String
    ): WeatherForecast.ForecastResponse

}