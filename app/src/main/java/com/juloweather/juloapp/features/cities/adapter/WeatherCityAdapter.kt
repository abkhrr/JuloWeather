package com.juloweather.juloapp.features.cities.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juloweather.juloapp.databinding.ViewRvItemCityBinding
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.juloapp.features.cities.adapter.click.OnClick
import com.juloweather.juloapp.features.cities.adapter.click.OnClickListener
import com.juloweather.juloapp.features.cities.adapter.click.SelectedCityClicked
import com.juloweather.utils.recyclerview.DiffUtil
import kotlin.properties.Delegates

class WeatherCityAdapter(val listener: OnClickListener): RecyclerView.Adapter<WeatherCityAdapter.ViewHolder>(), DiffUtil {

    var data: List<WeatherEntities> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems == newItems }
    }

    private var selectedPosition by Delegates.observable(0) { property, oldPos, newPos ->
        if (newPos in data.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewRvItemCityBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position in data.indices){
            holder.bind(data[position], position)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ViewRvItemCityBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: WeatherEntities, position: Int){
            setCardBgColor(position == selectedPosition)
            binding.viewTextLocale.text = item.name
            binding.itemClick           = OnClick { listener.adapterFavClicked(item) }

            binding.selectedClick = SelectedCityClicked {
                selectedPosition  = position
                listener.adapterSelectedCIty(item.lat, item.lon, "${item.name}")
            }
            binding.executePendingBindings()
        }

        private fun setCardBgColor(isSelected: Boolean){
            if(isSelected) selectedBg()
            else defaultBg()
        }

        fun defaultBg() {
            binding.cardSelection.setCardBackgroundColor(Color.parseColor("#DCF5FF"))
        }

        fun selectedBg() {
            binding.cardSelection.setCardBackgroundColor(Color.WHITE)
        }
    }

}