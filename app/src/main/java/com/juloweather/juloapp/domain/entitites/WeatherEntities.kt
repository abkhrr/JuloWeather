package com.juloweather.juloapp.domain.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntities(
    @PrimaryKey var id: Int,
    var lat: Double,
    var lon: Double,
    var name: String?
)