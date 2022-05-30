package com.juloweather.juloapp.features.cities.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.juloweather.juloapp.base.BaseFragment
import com.juloweather.juloapp.base.navigation.NavigationCommand
import com.juloweather.juloapp.databinding.FragmentSavedCitiesBinding
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.features.cities.adapter.WeatherCityAdapter
import com.juloweather.juloapp.features.cities.adapter.click.OnClickListener
import com.juloweather.juloapp.features.cities.viewmodel.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class SavedCitiesFragment : BaseFragment<FragmentSavedCitiesBinding, CitiesViewModel>() , OnClickListener {

    override val binding: FragmentSavedCitiesBinding by lazy { FragmentSavedCitiesBinding.inflate(layoutInflater) }
    override val viewModel: CitiesViewModel by viewModels()
    override val backToPreviousFragmentOnBackPressed: Boolean = true
    private val citiesAdapter by lazy { WeatherCityAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        binding.citiesRv.adapter = citiesAdapter
        binding.btnAdd.setOnClickListener { navigate(NavigationCommand.To(SavedCitiesFragmentDirections.toAddCity())) }
        binding.btnBack.setOnClickListener { navigate(NavigationCommand.Back) }
        viewModel.getSavedCities()
        viewModel.dataCities.observe(viewLifecycleOwner) {
            citiesAdapter.data = it
        }
    }

    override fun adapterFavClicked(item: WeatherEntities) {
        viewModel.removeFromFav(item)
        viewModel.dataCities.observe(viewLifecycleOwner) {
            citiesAdapter.data = it
        }
    }

    override fun adapterSelectedCIty(weatherId: Int, cityName: String) {
        navigate(NavigationCommand.To(SavedCitiesFragmentDirections.toHome(weatherId.toString(), cityName)))
    }
}