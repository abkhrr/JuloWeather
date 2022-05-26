package com.juloweather.juloapp.di

import com.juloweather.juloapp.JuloWeatherApp
import com.juloweather.juloapp.base.config.WebApiProvider
import com.juloweather.juloapp.data.local.db.dao.WeatherDao
import com.juloweather.juloapp.data.remote.WeatherApiService
import com.juloweather.juloapp.data.repositoryImpl.WeatherRepositoryImpl
import com.juloweather.juloapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideWebApiProvider(): WebApiProvider = WebApiProvider

    @Singleton
    @Provides
    fun provideRetrofit(
        webApiProvider: WebApiProvider,
        myApplication: JuloWeatherApp,
    ): Retrofit = webApiProvider.getRetrofit(myApplication.getConfigBaseUrl(), myApplication.getConfigApiKeys(), myApplication.isDebugMode())

    @Singleton
    @Provides
    fun provideWeatherRepository(authApiService: WeatherApiService, weatherDao: WeatherDao): WeatherRepository =
        WeatherRepositoryImpl(
            authApiService,
            weatherDao
        )

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApiService = retrofit.create(
        WeatherApiService::class.java
    )
}