package com.juloweather.juloapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.domain.repository.WeatherRepository
import com.juloweather.juloapp.features.home.viewmodel.HomeViewModel
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weather: WeatherForecast.WeatherResponse

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        weather           = mockk()
        weatherRepository = mockk()
        viewModel         = HomeViewModel(weatherRepository)
    }

    @Test
    fun test_Api_Return() {
        val lon    = 106.8451
        val lat    = -6.2146
        viewModel.getSelectedCityForeCast(lat, lon)
        val data   = viewModel.weatherData.value
        assertEquals(lat, data?.lat)
    }

}