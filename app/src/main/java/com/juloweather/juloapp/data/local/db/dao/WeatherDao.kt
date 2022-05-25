package com.juloweather.juloapp.data.local.db.dao

import androidx.room.*
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import io.reactivex.Flowable

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherEntities")
    suspend fun getFavoriteCities(): List<WeatherEntities>

    @Query("SELECT * FROM WeatherEntities WHERE id = :id")
    suspend fun getFavoriteCity(id: Int): WeatherEntities

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherEntities: WeatherEntities): Long

    @Delete
    suspend fun delete(t: WeatherEntities): Int
}