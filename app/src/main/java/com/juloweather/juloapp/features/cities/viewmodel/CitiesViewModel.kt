package com.juloweather.juloapp.features.cities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juloweather.juloapp.base.viewmodel.BaseViewModel
import com.juloweather.juloapp.data.local.AppPreferences
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(private val weatherRepository: WeatherRepository, private val appPreferences: AppPreferences): BaseViewModel() {

    private val _dataCities = MutableLiveData<List<WeatherEntities>>()
    val dataCities: LiveData<List<WeatherEntities>> get() = _dataCities

    fun getSavedCities(){
        viewModelScope.launch {
            _dataCities.value = weatherRepository.getFavoriteCities()
        }
    }

    fun removeFromFav(t: WeatherEntities){
        viewModelScope.launch {
            weatherRepository.deleteFavoriteCity(t)
        }
    }

    fun insertSelectedCityLanLon(lat: Double, lon: Double, cityName: String){
        appPreferences.setSelectedCityLon(lon)
        appPreferences.setSelectedLat(lat)
        appPreferences.setSelectedCityName(cityName)
    }

    init {
        getSavedCities()
    }

}