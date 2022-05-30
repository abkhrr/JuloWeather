package com.juloweather.juloapp.features.cities.adapter.click

class SelectedCityClicked(private val adapterSelectedCityClicked: () -> Unit) {
    fun adapterSelectedCityClicked() = adapterSelectedCityClicked.invoke()
}