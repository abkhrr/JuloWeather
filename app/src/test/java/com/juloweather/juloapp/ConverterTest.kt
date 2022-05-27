package com.juloweather.juloapp

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.math.roundToInt

@RunWith(RobolectricTestRunner::class)
class ConverterTest {

    @Test
    fun test_kelvinToCelsiusConversion_isCorrect() {
        val kelvinTemperature  = 298.6
        val celsiusTemperature = 25
        val tempInCelsius      = (kelvinTemperature + -273.15).roundToInt()

        assertThat(tempInCelsius).isEqualTo(celsiusTemperature)
    }
}