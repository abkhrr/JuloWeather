package com.juloweather.juloapp.features.cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juloweather.juloapp.databinding.ViewRvItemCityBinding
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.utils.recyclerview.DiffUtil
import kotlin.properties.Delegates

class WeatherCityAdapter: RecyclerView.Adapter<WeatherCityAdapter.ViewHolder>(), DiffUtil {

    var data: List<WeatherEntities> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems == newItems }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewRvItemCityBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ViewRvItemCityBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: WeatherEntities){
            binding.viewTextLocale.text = item.name
            binding.executePendingBindings()
        }
    }

}