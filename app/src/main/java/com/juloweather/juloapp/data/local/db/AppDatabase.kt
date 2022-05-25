package com.juloweather.juloapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juloweather.juloapp.data.local.db.dao.WeatherDao
import com.juloweather.juloapp.domain.entitites.WeatherEntities

@Database(
    entities = [WeatherEntities::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
