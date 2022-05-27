package com.juloweather.juloapp.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juloweather.juloapp.databinding.ViewRvItemHomeBinding
import com.juloweather.juloapp.domain.entitites.WeatherEntities
import com.juloweather.utils.recyclerview.DiffUtil
import kotlin.properties.Delegates

class HomeLayoutAdapter: RecyclerView.Adapter<HomeLayoutAdapter.ViewHolder>(), DiffUtil {

    var data: List<WeatherEntities> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems.id == newItems.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewRvItemHomeBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding: ViewRvItemHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: )
    }
}