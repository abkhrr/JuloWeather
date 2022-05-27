package com.juloweather.juloapp.di

import androidx.lifecycle.ViewModel
import com.juloweather.juloapp.base.viewmodel.ViewModelKey
import com.juloweather.juloapp.features.addcity.viewmodel.AddCityViewModel
import com.juloweather.juloapp.features.cities.viewmodel.CitiesViewModel
import com.juloweather.juloapp.features.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
abstract class FeatureViewModelModule {

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(AddCityViewModel::class)
    abstract fun provideAddCityViewModel(viewModel: AddCityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(CitiesViewModel::class)
    abstract fun provideCitiesViewModel(viewModel: CitiesViewModel): ViewModel

}