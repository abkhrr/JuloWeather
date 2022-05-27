package com.juloweather.juloapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.juloweather.juloapp.base.api.ApiResponse
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.domain.repository.WeatherRepository
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepoTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weather: WeatherForecast.WeatherResponse

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        weather           = mockk()
        weatherRepository = mockk()
    }

    @Test
    fun test_Api_Repo_Return() {
        val lon    = 106.8451
        val lat    = -6.2146
        runBlocking {
            when(val data = weatherRepository.getForecast(lat, lon, "Hourly,Minutely")){
                is ApiResponse.Success<WeatherForecast.ForecastResponse> -> {
                    assertEquals(lat, data.data.lat)
                }
                else -> {}
            }

        }
    }

}