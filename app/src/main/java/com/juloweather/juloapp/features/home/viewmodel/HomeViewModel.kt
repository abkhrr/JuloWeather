package com.juloweather.juloapp.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juloweather.juloapp.base.api.ApiResponse
import com.juloweather.juloapp.base.viewmodel.BaseViewModel
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: WeatherRepository): BaseViewModel() {

    private var _weatherData = MutableLiveData<WeatherForecast.ForecastResponse>()
    val weatherData: LiveData<WeatherForecast.ForecastResponse> get() = _weatherData

    fun getCurrentUserLocationForecast(lat: Double, lon: Double){
        viewModelScope.launch {
            when(val result = weatherRepository.getForecast(lat, lon, "Minutely, Hourly")){
                is ApiResponse.Success<WeatherForecast.ForecastResponse> -> {
                    _weatherData.value = result.data
                }
                is ApiResponse.ApiError -> {

                }
                is ApiResponse.NetworkError -> {

                }
                is ApiResponse.SocketError -> {

                }
            }
        }
    }

}