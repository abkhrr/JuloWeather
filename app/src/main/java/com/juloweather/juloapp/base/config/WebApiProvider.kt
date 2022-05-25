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

    fun getRetrofit(): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(buildRetrofitClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun buildRetrofitClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(120, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor   = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor { chain ->
                val request    = chain.request().url.newBuilder().addQueryParameter(Const.API_KEY_QUERY, BuildConfig.X_API_KEY).build()
                val newRequest = chain.request().newBuilder().url(request).build()
                chain.proceed(newRequest)
            }
        } else {
            builder.addInterceptor { chain ->
                val request    = chain.request().url.newBuilder().addQueryParameter(Const.API_KEY_QUERY, BuildConfig.X_API_KEY).build()
                val newRequest = chain.request().newBuilder().url(request).build()
                chain.proceed(newRequest)
            }
        }
        return builder.build()
    }

}