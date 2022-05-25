package com.juloweather.juloapp.base.api

import com.google.gson.Gson
import com.juloweather.utils.api.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T: Any> safeApiCall(apiCall: suspend () -> T): ApiResponse<T> {
    return withContext(Dispatchers.IO) {
        try {
            ApiResponse.Success(apiCall.invoke())
        } catch (e: java.lang.Exception) {
            when (e) {
                is IOException            -> ApiResponse.NetworkError
                is SocketTimeoutException -> ApiResponse.SocketError
                is HttpException -> {
                    val code          = e.code()
                    val errorResponse = convertErrorBody(e)
                    ApiResponse.ApiError(e.message(), code, errorResponse, TypeError.ERROR_HTTP)
                }
                else -> ApiResponse.ApiError(e.localizedMessage, Int.MAX_VALUE, null, TypeError.NETWORK_ERROR)
            }
        }
    }
}

fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        val gson = Gson()
        gson.fromJson(throwable.response()?.errorBody()?.string(), ErrorResponse::class.java)
    } catch (exception: Exception) {
        null
    }
}