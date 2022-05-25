package com.juloweather.utils.ext.textview

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

fun getStringTextFromTextInput(editText: EditText?): String {
    val textInputEditText = editText as TextInputEditText
    return textInputEditText.text.toString()
}

fun clearInputEditText(editText: EditText?){
    val textInputEditText = editText as TextInputEditText
    editText.text?.clear()
}

fun TextView.createNotHaveAccountSpannableENString(text: String){
    val spannableStringBuilder = SpannableStringBuilder(text)
    val foregroundColorRed     = ForegroundColorSpan(Color.rgb(214, 34, 34))
    val foregroundColorBlack   = ForegroundColorSpan(Color.rgb(0, 0, 0))
    val typeFace               = StyleSpan(Typeface.BOLD)

    spannableStringBuilder.setSpan(foregroundColorRed, 20, 30, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannableStringBuilder.setSpan(foregroundColorBlack, 0, 19, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannableStringBuilder.setSpan(typeFace, 20, 30, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    this.text = spannableStringBuilder
}

fun TextView.createNotHaveAccountSpannableIDString(text: String){
    val spannableStringBuilder = SpannableStringBuilder(text)
    val foregroundColorRed     = ForegroundColorSpan(Color.rgb(214, 34, 34))
    val foregroundColorBlack   = ForegroundColorSpan(Color.rgb(0, 0, 0))
    val typeFace               = StyleSpan(Typeface.BOLD)

    spannableStringBuilder.setSpan(foregroundColorRed, 18, 31, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannableStringBuilder.setSpan(foregroundColorBlack, 0, 17, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannableStringBuilder.setSpan(typeFace, 18, 31, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    this.text = spannableStringBuilder
}

fun TextView.createHaveAccountSpannableENString(text: String){
    val spannableStringBuilder = SpannableStringBuilder(text)
    val foregroundColorRed     = ForegroundColorSpan(Color.rgb(214, 34, 34))
    val foregroundColorBlack   = ForegroundColorSpan(Color.rgb(0, 0, 0))
    val typeFace               = StyleSpan(Typeface.BOLD)

    spannableStringBuilder.setSpan(foregroundColorRed, 14, 25, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannableStringBuilder.setSpan(foregroundColorBlack, 0, 13, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannableStringBuilder.setSpan(typeFace, 14, 25, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    this.text = spannableStringBuilder
}

fun TextView.createHaveAccountSpannableIDString(text: String){
    val spannableStringBuilder = SpannableStringBuilder(text)
    val foregroundColorRed     = ForegroundColorSpan(Color.rgb(214, 34, 34))
    val foregroundColorBlack   = ForegroundColorSpan(Color.rgb(0, 0, 0))
    val typeFace               = StyleSpan(Typeface.BOLD)

    spannableStringBuilder.setSpan(foregroundColorRed, 15, 26, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannableStringBuilder.setSpan(foregroundColorBlack, 0, 14, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannableStringBuilder.setSpan(typeFace, 15, 26, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    this.text = spannableStringBuilder
}

fun isTextSame(text: String, text2: String): Boolean {
    return text == text2
}