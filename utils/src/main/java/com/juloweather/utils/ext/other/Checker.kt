package com.juloweather.utils.ext.other

import java.util.regex.Pattern

fun isMobileNumberValid(mobile: String): Boolean {
    val p = Pattern.compile("^((?:\\+62|62)|0)[8]{1}[0-9]{9,12}\$")
    return p.matcher(mobile).matches()
}

fun isEmailValid(email: String): Boolean {
    val p = Pattern.compile("[A-Z0-9a-z.-_]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}")
    return p.matcher(email).matches()
}

fun isPasswordValid(password: String): Boolean {
    val pattern = Pattern.compile("(?=.*\\d)(?=.*[a-zA-Z]).{6,}")
    return pattern.matcher(password).matches()
}

fun isFullNameValid(name: String): Boolean {
    val p = Pattern.compile("^[a-z A-Z ' -]{0,30}\$")
    return p.matcher(name).matches()
}