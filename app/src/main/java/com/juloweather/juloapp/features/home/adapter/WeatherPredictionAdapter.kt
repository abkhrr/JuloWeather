package com.juloweather.juloapp.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juloweather.juloapp.BuildConfig
import com.juloweather.juloapp.R
import com.juloweather.juloapp.databinding.ViewRvItemWeatherPredictionBinding
import com.juloweather.juloapp.domain.model.WeatherForecast
import com.juloweather.utils.date.DateUtils.convertLongToTime
import com.juloweather.utils.recyclerview.DiffUtil
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class WeatherPredictionAdapter: RecyclerView.Adapter<WeatherPredictionAdapter.ViewHolder>(), DiffUtil {

    var data: List<WeatherForecast.ForecastResponse.DailyForecast> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems == newItems }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewRvItemWeatherPredictionBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ViewRvItemWeatherPredictionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: WeatherForecast.ForecastResponse.DailyForecast){
            val celsiusFromKelvin   = data.temp?.max?.minus(273.15)
            val celsiusValue        = celsiusFromKelvin?.roundToInt()

            Glide.with(binding.root.context).load("${BuildConfig.BASE_IMAGE_URL}/${data.weather?.get(0)?.icon}.png").into(binding.viewWeatherIcon)
            binding.viewTextWeatherDesc.text = data.dt?.let { convertLongToTime(it) }
            binding.viewTextCelsius.text     = binding.root.resources.getString(R.string.percentage_value, celsiusValue)
            binding.executePendingBindings()
        }
    }
}