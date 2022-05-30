package com.juloweather.juloapp.features.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juloweather.juloapp.base.api.ApiResponse
import com.juloweather.juloapp.base.viewmodel.BaseViewModel
import com.juloweather.juloapp.data.local.prefs.AppPrefs
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val appPreferences: AppPrefs
): BaseViewModel() {

    private var _weatherData = MutableLiveData<WeatherForecast.ForecastResponse>()
    val weatherData: LiveData<WeatherForecast.ForecastResponse> get() = _weatherData

    fun getForeCast(id: Int) {
        if(isFirstLaunch()){
            getFirstLaunchForecast()
        } else {
            getSelectedCityForeCast(id)
        }
    }

    private fun getFirstLaunchForecast(){
        viewModelScope.launch {
            when(val result = weatherRepository.getForecast(-6.2146, 106.8451, "Minutely, Hourly")){
                is ApiResponse.Success<WeatherForecast.ForecastResponse> -> {
                    if(isFirstLaunch()){
                        saveDefaultLoc(result.data)
                    }
                    _weatherData.value = result.data
                }
                is ApiResponse.ApiError -> {
                    showSnack.value = result.message
                }
                is ApiResponse.NetworkError -> {
                    showSnack.value = "Error, Please Check Your Connections."
                }
                is ApiResponse.SocketError -> {
                    showSnack.value = "Error, Something Went Wrong."
                }
            }
        }
    }

    private fun getSelectedCityForeCast(id: Int){
        viewModelScope.launch {
            val weatherData = weatherRepository.getFavoriteCity(id)
            when(val result = weatherRepository.getForecast(weatherData.lat, weatherData.lon, "Minutely, Hourly")){
                is ApiResponse.Success<WeatherForecast.ForecastResponse> -> {
                    _weatherData.value = result.data
                }
                is ApiResponse.ApiError -> {
                    showSnack.value = result.message
                }
                is ApiResponse.NetworkError -> {
                    showSnack.value = "Error, Please Check Your Connections."
                }
                is ApiResponse.SocketError -> {
                    showSnack.value = "Error, Something Went Wrong."
                }
            }
        }
    }

    private fun saveDefaultLoc(data: WeatherForecast.ForecastResponse){
        viewModelScope.launch {
            val entities      = WeatherEntities(1642911, data.lat!!.toDouble(), data.lon!!.toDouble(), "Jakarta, ID")
            weatherRepository.insertFavoriteCity(entities)
            appPreferences.setIsFirstTimeLaunch(false)
        }
    }

    fun isFirstLaunch(): Boolean = appPreferences.isFirstTimeLaunch()
}