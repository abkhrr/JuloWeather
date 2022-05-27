package com.juloweather.utils.nework

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData

@Suppress("DEPRECATION") @SuppressLint("ObsoleteSdkInt")
class NetworkHelper(val context: Context) : LiveData<Boolean>() {

  private var connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

  private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

  private val networkRequestBuilder: NetworkRequest.Builder =
    NetworkRequest.Builder()
      .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
      .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)

  override fun onActive() {
    super.onActive()
    updateConnection()
    when {
      Build.VERSION.SDK_INT >= Build.VERSION_CODES.N        -> connectivityManager.registerDefaultNetworkCallback(getConnectivityMarshmallowManagerCallback())
      Build.VERSION.SDK_INT >= Build.VERSION_CODES.M        -> marshmallowNetworkAvailableRequest()
      Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> lollipopNetworkAvailableRequest()
      else -> {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
          context.registerReceiver(networkReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        }
      }
    }
  }

  override fun onInactive() {
    super.onInactive()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    } else {
      context.unregisterReceiver(networkReceiver)
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private fun lollipopNetworkAvailableRequest() {
    connectivityManager.registerNetworkCallback(networkRequestBuilder.build(), getConnectivityLollipopManagerCallback())
  }

  @TargetApi(Build.VERSION_CODES.M)
  private fun marshmallowNetworkAvailableRequest() {
    connectivityManager.registerNetworkCallback(networkRequestBuilder.build(), getConnectivityMarshmallowManagerCallback())
  }

  private fun getConnectivityLollipopManagerCallback(): ConnectivityManager.NetworkCallback {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
          postValue(true)
        }

        override fun onLost(network: Network) {
          postValue(false)
        }
      }
      return connectivityManagerCallback
    } else {
      throw IllegalAccessError("Accessing wrong API version")
    }
  }

  private fun getConnectivityMarshmallowManagerCallback(): ConnectivityManager.NetworkCallback {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

      connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
          networkCapabilities.let { capabilities ->
            if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
              postValue(true)
            }
          }
        }

        override fun onLost(network: Network) {
          postValue(false)
        }
      }
      return connectivityManagerCallback
    } else {
      throw IllegalAccessError("Accessing wrong API version")
    }
  }

  private val networkReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      updateConnection()
    }
  }

  private fun updateConnection() {
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    postValue(activeNetwork?.isConnected == true)
  }

  companion object{
    private val TAG = NetworkHelper::class.java.simpleName
  }
}