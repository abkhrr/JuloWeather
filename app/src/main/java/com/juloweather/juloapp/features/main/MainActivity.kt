package com.juloweather.juloapp.features.main

import android.Manifest
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import com.juloweather.juloapp.R
import com.juloweather.juloapp.databinding.ActivityMainBinding
import com.juloweather.utils.ext.activity.adjustFontScale
import com.juloweather.utils.nework.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog
    private var isAlreadyVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adjustFontScale(resources?.configuration!!)
        initDialog()
        checkConnection()
        binding.executePendingBindings()
    }

    private fun checkConnection(){
        val networkHelper = NetworkHelper(this)
        networkHelper.observe(this) { isConnected ->
            if (isConnected) {
                hideDisplayInternetNotAvailable()
                isAlreadyVisible = false
            } else {
                if (!isAlreadyVisible) {
                    displayInternetNotAvailable()
                    isAlreadyVisible = true
                }
            }
        }
    }

    private fun displayInternetNotAvailable(){
        val layoutInflater   = layoutInflater
        val layoutView: View = layoutInflater.inflate(R.layout.custom_dialog, null)

        dialogBuilder.setCancelable(false)
        dialogBuilder.setView(layoutView)

        alertDialog   = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    private fun hideDisplayInternetNotAvailable(){
        if(isAlreadyVisible)
        {
            alertDialog.dismiss()
        }
    }

    private fun initDialog(){
        dialogBuilder = AlertDialog.Builder(ContextThemeWrapper(this,android.R.style.ThemeOverlay_Material_Dialog))
    }


}