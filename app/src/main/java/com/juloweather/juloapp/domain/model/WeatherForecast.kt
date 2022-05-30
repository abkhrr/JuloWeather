package com.juloweather.juloapp.domain.model

import com.google.gson.annotations.SerializedName

object WeatherForecast {

    data class WeatherResponse(
        @SerializedName("base")
        var base: String?,

        @SerializedName("clouds")
        var clouds: Clouds?,

        @SerializedName("cod")
        var cod: Int?,

        @SerializedName("coord")
        var coord: Coord?,

        @SerializedName("dt")
        var dt: Long?,

        @SerializedName("id")
        var id: Int?,

        @SerializedName("main")
        var main: WeatherDataMain?,

        @SerializedName("name")
        var name: String?,

        @SerializedName("rain")
        var rain: ForecastResponse.RainForecast?,

        @SerializedName("sys")
        var sys: Sys?,

        @SerializedName("timezone")
        var timezone: Int?,

        @SerializedName("visibility")
        var visibility: Int?,

        @SerializedName("weather")
        var weather: List<ForecastResponse.WeatherForecast>?,

        @SerializedName("wind")
        var wind: Wind?
    ) {
        data class Clouds(
            @SerializedName("all")
            var all: Int?
        )

        data class Coord(
            @SerializedName("lat")
            var lat: Double?,

            @SerializedName("lon")
            var lon: Double?
        )

        data class WeatherDataMain(
            @SerializedName("feels_like")
            var feelsLike: Double?,

            @SerializedName("humidity")
            var humidity: Int?,

            @SerializedName("pressure")
            var pressure: Int?,

            @SerializedName("temp")
            var temp: Double?,

            @SerializedName("temp_max")
            var tempMax: Double?,

            @SerializedName("temp_min")
            var tempMin: Double?
        )

        data class Wind(
            @SerializedName("deg")
            var deg: Int?,

            @SerializedName("gust")
            var gust: Double?,

            @SerializedName("speed")
            var speed: Double?
        )

        data class Sys(
            @SerializedName("country")
            var country: String?,

            @SerializedName("id")
            var id: Int?,

            @SerializedName("sunrise")
            var sunrise: Int?,

            @SerializedName("sunset")
            var sunset: Int?,

            @SerializedName("type")
            var type: Int?
        )
    }

    data class ForecastResponse(
        @SerializedName("current")
        var current: CurrentForecast?,

        @SerializedName("daily")
        var daily: List<DailyForecast>?,

        @SerializedName("lat")
        var lat: Double?,

        @SerializedName("lon")
        var lon: Double?,

        @SerializedName("timezone")
        var timezone: String?,

        @SerializedName("timezone_offset")
        var timezoneOffset: Int?
    ) {
        data class WeatherForecast(
            @SerializedName("description")
            var description: String?,

            @SerializedName("icon")
            var icon: String?,

            @SerializedName("id")
            var id: Int?,

            @SerializedName("main")
            var main: String?
        )

        data class CurrentForecast(
            @SerializedName("clouds")
            var clouds: Int?,

            @SerializedName("dew_point")
            var dewPoint: Double?,

            @SerializedName("dt")
            var dt: Long?,

            @SerializedName("feels_like")
            var feelsLike: Double?,

            @SerializedName("humidity")
            var humidity: Int?,

            @SerializedName("pressure")
            var pressure: Int?,

            @SerializedName("rain")
            var rain: RainForecast?,

            @SerializedName("sunrise")
            var sunrise: Int?,

            @SerializedName("sunset")
            var sunset: Int?,

            @SerializedName("temp")
            var temp: Double?,

            @SerializedName("uvi")
            var uvi: Double?,

            @SerializedName("visibility")
            var visibility: Int?,

            @SerializedName("weather")
            var weather: List<WeatherForecast>?,

            @SerializedName("wind_deg")
            var windDeg: Int?,

            @SerializedName("wind_gust")
            var windGust: Double?,

            @SerializedName("wind_speed")
            var windSpeed: Double?
        )

        data class DailyForecast(
            @SerializedName("clouds")
            var clouds: Int?,

            @SerializedName("dew_point")
            var dewPoint: Double?,

            @SerializedName("dt")
            var dt: Long?,

            @SerializedName("feels_like")
            var feelsLike: FeelsLikeForecast?,

            @SerializedName("humidity")
            var humidity: Int?,

            @SerializedName("moon_phase")
            var moonPhase: Double?,

            @SerializedName("moonrise")
            var moonrise: Int?,

            @SerializedName("moonset")
            var moonset: Int?,

            @SerializedName("pop")
            var pop: Double?,

            @SerializedName("pressure")
            var pressure: Int?,

            @SerializedName("rain")
            var rain: Double?,

            @SerializedName("sunrise")
            var sunrise: Int?,

            @SerializedName("sunset")
            var sunset: Int?,

            @SerializedName("temp")
            var temp: ForecastTemperature?,

            @SerializedName("uvi")
            var uvi: Double?,

            @SerializedName("weather")
            var weather: List<WeatherForecast>?,

            @SerializedName("wind_deg")
            var windDeg: Int?,

            @SerializedName("wind_gust")
            var windGust: Double?,

            @SerializedName("wind_speed")
            var windSpeed: Double?
        )

        data class FeelsLikeForecast(
            @SerializedName("day")
            var day: Double?,

            @SerializedName("eve")
            var eve: Double?,

            @SerializedName("morn")
            var morn: Double?,

            @SerializedName("night")
            var night: Double?
        )

        data class RainForecast(
            @SerializedName("1h")
            var h: Double?
        )

        data class ForecastTemperature(
            @SerializedName("day")
            var day: Double?,

            @SerializedName("eve")
            var eve: Double?,

            @SerializedName("max")
            var max: Double?,

            @SerializedName("min")
            var min: Double?,

            @SerializedName("morn")
            var morn: Double?,

            @SerializedName("night")
            var night: Double?
        )
    }

}