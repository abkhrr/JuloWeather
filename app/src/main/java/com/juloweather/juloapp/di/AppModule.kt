package com.juloweather.juloapp.di

import android.content.Context
import androidx.room.Room
import com.juloweather.juloapp.BuildConfig
import com.juloweather.juloapp.JuloWeatherApp
import com.juloweather.juloapp.data.local.AppPreferences
import com.juloweather.juloapp.data.local.PreferenceImpl
import com.juloweather.juloapp.data.local.db.AppDatabase
import com.juloweather.utils.constant.Const
import com.juloweather.utils.qualifier.PreferenceInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): JuloWeatherApp {
        return app as JuloWeatherApp
    }

    @Provides
    @PreferenceInfo
    fun providePrefName(): String = Const.PREFS_NAME

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.APPLICATION_ID).build()

    @Singleton
    @Provides
    fun provideWeatherDao(db: AppDatabase) = db.weatherDao()

    @Singleton
    @Provides
    fun provideAppPrefs(preferenceImpl: PreferenceImpl): AppPreferences {
        return preferenceImpl
    }

}