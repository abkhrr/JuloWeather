package com.juloweather.juloapp.base.api

import com.juloweather.utils.api.TypeError

sealed class ApiResponse<out T: Any> {
    data class Success<out T: Any>(val data: T) : ApiResponse<T>()
    data class ApiError(val message: String? = null, val statusCode: Int? = null, val errorBody: ErrorResponse? = null, val typeError: TypeError) : ApiResponse<Nothing>()
    object SocketError: ApiResponse<Nothing>()
    object NetworkError: ApiResponse<Nothing>()
}