package com.juloweather.juloapp.features.addcity.viewmodel

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
class AddCityViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): BaseViewModel() {

    private var _weatherData = MutableLiveData<WeatherForecast.WeatherResponse>()
    val weatherData: LiveData<WeatherForecast.WeatherResponse> get() = _weatherData

    private var _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun searchCityByCityName(cityName: String){
        viewModelScope.launch {
            when(val result = weatherRepository.getCurrentWeatherByCityName(cityName)){
                is ApiResponse.Success<WeatherForecast.WeatherResponse> -> {
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

    fun checkFavorite(data: WeatherForecast.WeatherResponse): Boolean {
        var alreadyFavorite: Boolean = true
        viewModelScope.launch {
            val weatherData = data.id?.let { weatherRepository.getFavoriteCity(it) }
            alreadyFavorite = weatherData != null
        }
        return alreadyFavorite
    }

    fun insertToFavorite(data: WeatherForecast.WeatherResponse){
        viewModelScope.launch {
            val entities      = WeatherEntities(data.id!!.toInt(), data.coord?.lat!!.toDouble(), data.coord?.lon!!.toDouble(), data.name)
            weatherRepository.insertFavoriteCity(entities)
            _isFavorite.value = false
        }
    }

    fun deleteFromFavorite(data: WeatherForecast.WeatherResponse){
        viewModelScope.launch {
            val entities     = WeatherEntities(data.id!!.toInt(), data.coord?.lat!!.toDouble(), data.coord?.lon!!.toDouble(), data.name)
            weatherRepository.deleteFavoriteCity(entities)
            _isFavorite.value = false
        }
    }

    private var _selectedCityName = MutableLiveData<String>()
    val selectedCityName: LiveData<String> get() = _selectedCityName

    private fun setSelectedCityName() {
        _selectedCityName.value = ""
    }

    val selectedLon: Double      = 1.0
    val selectedLat: Double      = 1.0

    init {
        setSelectedCityName()
    }

}