package com.juloweather.juloapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.juloweather.juloapp.base.viewmodel.BaseViewModel
import com.juloweather.utils.ext.activity.adjustFontScale

abstract class BaseActivity<VB: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {

    abstract val binding: VB
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding(){
        setContentView(binding.root)
        adjustFontScale(resources?.configuration!!)
        binding.executePendingBindings()
    }


}