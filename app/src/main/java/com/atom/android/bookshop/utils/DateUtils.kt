package com.atom.android.bookshop.utils

import java.text.ParseException
import java.text.SimpleDateFormat

fun convertStringToDate(string: String?): String {
    if (string == null || string.isEmpty()) return Constants.DEFAULT_STRING
    return try {
        val inputFormat = SimpleDateFormat(Constants.FORMAT_DATE_TIME_INPUT)
        val outputFormat = SimpleDateFormat(Constants.FORMAT_DATE_TIME)
        val date = inputFormat.parse(string)
        outputFormat.format(date).toString()
    } catch (ex: ParseException) {
        Constants.DEFAULT_STRING
    }
}
