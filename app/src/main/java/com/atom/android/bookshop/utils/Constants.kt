package com.atom.android.bookshop.utils

object Constants {
    private const val PROJECT_NAME = "com.atom.android.bookshop."

    const val NUMBER_PHONE_PATTERN = "([0-9]{9,10})\\b"
    const val EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$"
    const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"

    const val SHARED_PREF = PROJECT_NAME + "SHARED_PREF"
    const val SHARED_PREF_DEFAULT_STRING = ""
    const val SHARED_PREF_TOKEN_LOGIN = PROJECT_NAME + "SHARED_PREF_TOKEN_LOGIN"

}
