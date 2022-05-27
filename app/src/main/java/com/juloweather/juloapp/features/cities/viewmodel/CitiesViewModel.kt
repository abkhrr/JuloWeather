package com.juloweather.juloapp.features.cities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juloweather.juloapp.base.viewmodel.BaseViewModel
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(private val weatherRepository: WeatherRepository): BaseViewModel() {

    private val _dataCities = MutableLiveData<List<WeatherEntities>>()
    val dataCities: LiveData<List<WeatherEntities>> get() = _dataCities

    fun getSavedCities(){
        viewModelScope.launch {
            _dataCities.value = weatherRepository.getFavoriteCities()
        }
    }

    init {
        getSavedCities()
    }

}