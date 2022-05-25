package com.juloweather.juloapp.features.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juloweather.juloapp.R
import com.juloweather.juloapp.databinding.ActivityLauncherBinding
import com.juloweather.juloapp.features.main.MainActivity
import com.juloweather.utils.ext.intent.launchActivityAndFinish
import com.juloweather.utils.ext.intent.launchDelayedActivityAndFinish
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    private val binding: ActivityLauncherBinding by lazy { ActivityLauncherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initSplashScreen()
        binding.executePendingBindings()
    }

    private fun initSplashScreen(){
        launchDelayedActivityAndFinish<MainActivity>()
    }

}