package com.juloweather.juloapp.base.config

import android.content.Context
import com.google.gson.GsonBuilder
import com.juloweather.juloapp.BuildConfig
import com.juloweather.utils.constant.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebApiProvider {

    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

    fun getRetrofit(configUrl: String, configXApiKeys: String, isDebugMode: Boolean): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(configUrl)
        .client(buildRetrofitClient(configXApiKeys, isDebugMode))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    private fun buildRetrofitClient(configXApiKeys: String, isDebugMode: Boolean): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(120, TimeUnit.SECONDS)

        if (isDebugMode) {
            val interceptor   = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }

        builder.addInterceptor { chain ->
            val request    = chain.request().url.newBuilder().addQueryParameter(Const.API_KEY_QUERY, configXApiKeys).build()
            val newRequest = chain.request().newBuilder().url(request).build()
            chain.proceed(newRequest)
        }

        return builder.build()
    }

}