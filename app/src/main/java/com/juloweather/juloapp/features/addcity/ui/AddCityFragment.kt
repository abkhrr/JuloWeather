package com.juloweather.juloapp.features.addcity.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.juloweather.juloapp.BuildConfig
import com.juloweather.juloapp.R
import com.juloweather.juloapp.base.BaseFragment
import com.juloweather.juloapp.base.navigation.NavigationCommand
import com.juloweather.juloapp.databinding.FragmentAddCityBinding
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.juloapp.features.addcity.viewmodel.AddCityViewModel
import com.juloweather.juloapp.features.home.adapter.WeatherPredictionAdapter
import com.juloweather.utils.date.DateUtils
import com.juloweather.utils.ext.view.gone
import com.juloweather.utils.ext.view.visible
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlin.math.roundToInt

@AndroidEntryPoint
@FragmentScoped
class AddCityFragment : BaseFragment<FragmentAddCityBinding, AddCityViewModel>() {

    @FragmentScoped
    override val binding: FragmentAddCityBinding by lazy { FragmentAddCityBinding.inflate(layoutInflater) }
    override val viewModel: AddCityViewModel by viewModels()
    override val backToPreviousFragmentOnBackPressed: Boolean = true
    private var isFavorite = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews(){
        binding.btnBack.setOnClickListener {
            binding.layoutContainer.gone()
            navigate(NavigationCommand.Back)
        }
        binding.btnSearch.setOnClickListener {
            val cityName = binding.viewEditTextCity.editText?.text.toString()
            viewModel.searchCityByCityName(cityName)
            setupCityWeatherView()
        }
    }

    private fun setupCityWeatherView(){
        viewModel.weatherData.observe(viewLifecycleOwner) { data ->
            binding.layoutContainer.visible()
            val celsiusFromKelvin = data.main?.temp?.minus(273.15)
            val celsiusValue      = celsiusFromKelvin?.roundToInt()
            viewModel.checkFavorite(data)
            observeDataFav()
            binding.buttonFav.setOnClickListener { setFavorite(data) }

            with(binding) {
                Glide.with(binding.root.context).load("${BuildConfig.BASE_IMAGE_URL}/${data.weather?.get(0)?.icon}.png").into(viewIconWeather)
                viewModel.selectedCityName.observe(viewLifecycleOwner) { cityAndCountry -> binding.viewTextLocale.text = cityAndCountry }
                viewLocaleDate.text        = resources.getString(R.string.date_today, data.dt?.let { DateUtils.convertLongToTime(it) })
                viewTextCelsius.text       = resources.getString(R.string.percentage_value, celsiusValue)
                viewTextHumidityValue.text = resources.getString(R.string.percentage_value, data.main?.humidity)
                viewTextWeatherDesc.text   = data.weather?.get(0)?.description
                viewTextWindValue.text     = (data.wind?.speed?.times(3.6)).toString()
            }
        }
    }

    private fun observeDataFav(){
        viewModel.isFavorite.observe(viewLifecycleOwner){
            isFavorite = it
            setFavoriteIcon()
        }
    }

    private fun setFavorite(data: WeatherForecast.WeatherResponse) {
        isFavorite = if (!isFavorite) {
            viewModel.insertToFavorite(data)
            true
        } else {
            viewModel.deleteFromFavorite(data)
            !isFavorite
        }
    }

    private fun setFavoriteIcon() {
        binding.buttonFav.setImageDrawable(getFavoriteIcon())
    }

    private fun getFavoriteIcon(): Drawable? {
        return if (isFavorite) ContextCompat.getDrawable(
            requireContext(),
            R.drawable.ic_baseline_star_24
        ) else ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_star_outline_24)
    }

}