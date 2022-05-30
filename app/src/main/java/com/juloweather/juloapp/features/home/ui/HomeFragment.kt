package com.juloweather.juloapp.features.home.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.juloweather.juloapp.BuildConfig
import com.juloweather.juloapp.R
import com.juloweather.juloapp.base.BaseFragment
import com.juloweather.juloapp.base.navigation.NavigationCommand
import com.juloweather.juloapp.databinding.FragmentHomeBinding
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.features.home.adapter.WeatherPredictionAdapter
import com.juloweather.juloapp.features.home.viewmodel.HomeViewModel
import com.juloweather.utils.date.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlin.math.roundToInt

@AndroidEntryPoint
@FragmentScoped
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    @FragmentScoped
    override val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override val viewModel: HomeViewModel by viewModels()
    override val backToPreviousFragmentOnBackPressed: Boolean = false
    private val weatherPredictionAdapter by lazy { WeatherPredictionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupClick()
    }

    private fun setupClick(){
        binding.buttonManageCity.setOnClickListener {
            navigate(NavigationCommand.To(HomeFragmentDirections.toManageCity()))
        }
    }

    private fun setupData(){
        viewModel.getSelectedCityForeCast()
        viewModel.weatherData.observe(viewLifecycleOwner) { forecast ->
            setupDataWithViews(forecast)
        }
    }

    private fun setupDataWithViews(data: WeatherForecast.ForecastResponse){
        val celsiusFromKelvin = data.current?.temp?.minus(273.15)
        val celsiusValue      = celsiusFromKelvin?.roundToInt()

        with(binding){
            Glide.with(requireContext()).load("${BuildConfig.BASE_IMAGE_URL}/${data.current?.weather?.get(0)?.icon}.png").into(viewIconWeather)
            viewModel.selectedCityName.observe(viewLifecycleOwner) { cityAndCountry -> binding.viewTextLocale.text = cityAndCountry }
            viewLocaleDate.text        = resources.getString(R.string.date_today, data.current?.dt?.let { DateUtils.convertLongToTime(it) })
            viewTextCelsius.text       = resources.getString(R.string.celsius_value, celsiusValue)
            viewTextHumidityValue.text = resources.getString(R.string.percentage_value, data.current?.humidity)
            viewTextWeatherDesc.text   = data.current?.weather?.get(0)?.description
            viewTextWindValue.text     = (data.current?.windSpeed?.times(3.6)).toString()
            viewTextIndexUvValue.text  = data.current?.uvi?.roundToInt().toString()
        }
        setupRv(data.daily)
    }

    private fun setupRv(data: List<WeatherForecast.ForecastResponse.DailyForecast>?){
        binding.viewRvWeatherPrediction.apply {
            adapter         = weatherPredictionAdapter
            clipToPadding   = false
            onFlingListener = null
        }
        if (data != null) {
            weatherPredictionAdapter.data = data
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSelectedCityForeCast()
    }

}