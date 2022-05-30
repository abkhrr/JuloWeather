package com.juloweather.juloapp.features.cities.adapter.click

class OnClick(private val adapterFavClicked: () -> Unit) {
    fun adapterFavClicked() = adapterFavClicked.invoke()
}