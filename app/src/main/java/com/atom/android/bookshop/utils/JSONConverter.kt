package com.atom.android.bookshop.utils

import com.atom.android.bookshop.data.model.LoginEntity
import org.json.JSONObject

fun LoginEntity.convertToJson(): String {
    val jsonObject = JSONObject()
    val strJson =
        jsonObject.put(LoginEntity.EMAIL, this.userEmail)
        jsonObject.put(LoginEntity.PASSWORD, this.userPassword)
    return strJson.toString()
}
