package com.juloweather.juloapp.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juloweather.juloapp.base.api.ApiResponse
import com.juloweather.juloapp.base.viewmodel.BaseViewModel
import com.juloweather.juloapp.data.local.AppPreferences
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val appPreferences: AppPreferences
): BaseViewModel() {

    private var _weatherData = MutableLiveData<WeatherForecast.ForecastResponse>()
    val weatherData: LiveData<WeatherForecast.ForecastResponse> get() = _weatherData

    fun getSelectedCityForeCast(){
        viewModelScope.launch {
            when(val result = weatherRepository.getForecast(selectedLat(), selectedLon(), "Minutely, Hourly")){
                is ApiResponse.Success<WeatherForecast.ForecastResponse> -> {
                    saveDefaultLoc(result.data)
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
            appPreferences.setIsFirstLaunch(false)
            appPreferences.setSelectedLat(data.lat!!.toDouble())
            appPreferences.setSelectedCityLon(data.lon!!.toDouble())
        }
    }

    private var _selectedCityName = MutableLiveData<String>()
    val selectedCityName: LiveData<String> get() = _selectedCityName

    private val defaultSelectedLon: Double   = 106.845
    private val defaultSelectedLat: Double   = -6.2146
    private val isFirstTimeLaunch: Boolean   = appPreferences.isFirstLaunch()

    private fun selectedLon(): Double {
        return if(isFirstTimeLaunch){
            defaultSelectedLon
        } else appPreferences.getSelectedCityLon()
    }

    private fun selectedLat(): Double {
        return if(isFirstTimeLaunch){
            defaultSelectedLat
        } else appPreferences.getSelectedLat()
    }

    private fun getSelectedCityName() {
        if(isFirstTimeLaunch){
            _selectedCityName.value = "Jakarta, ID"
        } else _selectedCityName.value = appPreferences.getSelectedCityName()
    }

    init {
        getSelectedCityName()
    }

}