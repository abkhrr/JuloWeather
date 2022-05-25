package com.juloweather.utils.api

object ApiUtils {
    fun getErrorMessage(codes: Int?, type: TypeError, message: String): String {
        when(type) {

            TypeError.ERROR_SOCKET_TIMEOUT -> {
                return "Koneksi Terputus, Silahkan Cek Koneksi Anda!"
            }

            TypeError.ERROR_HTTP -> {
                return when(codes){
                    400  -> { "$message : 400" }
                    403  -> { "$message : 403" }
                    404  -> { "$message : 404" }
                    406  -> { "$message : 406" }
                    500  -> { "$message : 500" }
                    502  -> { "$message : 502" }
                    503  -> { "$message : 503" }
                    else -> { message }
                }
            }

            TypeError.NETWORK_ERROR -> {
                return "Terjadi Kesalahan"
            }
        }
    }
}