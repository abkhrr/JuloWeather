package com.juloweather.juloapp.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juloweather.juloapp.R
import com.juloweather.juloapp.databinding.ActivityMainBinding
import com.juloweather.utils.ext.activity.adjustFontScale
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adjustFontScale(resources?.configuration!!)
        binding.executePendingBindings()
    }

}