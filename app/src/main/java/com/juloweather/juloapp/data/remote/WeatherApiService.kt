package com.juloweather.juloapp.data.remote

import com.juloweather.juloapp.base.api.ApiEndPoint
import com.juloweather.juloapp.base.api.BaseObjectResponse
import com.juloweather.juloapp.domain.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {

    @GET(ApiEndPoint.GET_WEATHER_DATA)
    fun getCurrentWeatherByCityName(
        @Query("q") cityName: String,
    ): BaseObjectResponse<Forecast.WeatherResponse>

    @GET(ApiEndPoint.GET_FORECAST_DATA)
    fun getForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("exclude") exclude: String
    ): BaseObjectResponse<Forecast.WeatherResponse>

}