package com.atom.android.bookshop.utils

import java.text.ParseException
import java.text.SimpleDateFormat

fun convertStringToDate(string: String?): String {
    if (string == null || string.isEmpty()) return Constants.DEFAULT_STRING
    return try {
        val formatter = SimpleDateFormat(Constants.FORMAT_DATE_TIME)
        formatter.parse(string).toString()
    } catch (ex: ParseException) {
        Constants.DEFAULT_STRING
    }
}
