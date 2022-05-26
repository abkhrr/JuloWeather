package com.juloweather.utils.ext.fragment

import android.content.res.Configuration
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

@Suppress("DEPRECATION")
fun Fragment.adjustFontScale(configuration: Configuration) {
    configuration.fontScale = 1.0f

    val metrics = resources.displayMetrics
    val manager = ContextCompat.getSystemService(requireActivity(), WindowManager::class.java)

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        val display = activity?.display
        display?.getRealMetrics(metrics)
    } else {
        val display = activity?.windowManager?.defaultDisplay
        display?.getMetrics(metrics)
    }

    manager?.defaultDisplay?.getMetrics(metrics)
    metrics.scaledDensity = configuration.fontScale * metrics.density

    activity?.baseContext?.resources?.displayMetrics?.setTo(metrics)
}