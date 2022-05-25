package com.juloweather.juloapp.base.listener

interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}