package com.juloweather.juloapp.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juloweather.juloapp.base.api.ApiResponse
import com.juloweather.juloapp.base.viewmodel.BaseViewModel
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.domain.repository.WeatherRepository
import com.juloweather.utils.date.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: WeatherRepository): BaseViewModel() {

    private var _weatherData = MutableLiveData<WeatherForecast.ForecastResponse>()
    val weatherData: LiveData<WeatherForecast.ForecastResponse> get() = _weatherData

    fun getSelectedCityForeCast(lat: Double, lon: Double){
        viewModelScope.launch {
            when(val result = weatherRepository.getForecast(lon, lat, "Minutely, Hourly")){
                is ApiResponse.Success<WeatherForecast.ForecastResponse> -> {
                    saveDefaultLoc()
                    _weatherData.value = result.data
                }
                is ApiResponse.ApiError -> {
                    showSnack.value = "Error, Api Error."
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

    private fun saveDefaultLoc(){
        viewModelScope.launch {
            val entities      = WeatherEntities(1642911, selectedLat, selectedLon, "Jakarta, ID")
            weatherRepository.insertFavoriteCity(entities)
        }
    }

    private var _selectedCityName = MutableLiveData<String>()
    val selectedCityName: LiveData<String> get() = _selectedCityName

    private fun setSelectedCityName() {
        _selectedCityName.value = "Jakarta, ID"
    }

    val selectedLon: Double      = 106.845
    val selectedLat: Double      = -6.2146

    init {
        setSelectedCityName()
    }

}