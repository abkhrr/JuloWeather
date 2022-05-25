package com.juloweather.juloapp.base.viewmodel

import androidx.lifecycle.ViewModel
import com.juloweather.utils.event.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val isError: SingleLiveEvent<Boolean>   = SingleLiveEvent()
    val showToast: SingleLiveEvent<String>  = SingleLiveEvent()
    val showSnack: SingleLiveEvent<String>  = SingleLiveEvent()
}