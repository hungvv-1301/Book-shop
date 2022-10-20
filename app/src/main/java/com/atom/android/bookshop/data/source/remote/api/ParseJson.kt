package com.atom.android.bookshop.data.source.remote.api

import com.atom.android.bookshop.data.model.User
import com.atom.android.bookshop.data.source.remote.ResponseObject
import com.atom.android.bookshop.utils.getUser
import org.json.JSONObject

fun convertJsonToResponseObject(jsonObject: JSONObject): ResponseObject<String> {

    val status = jsonObject.getBoolean(ApiConstants.Response.STATUS)
    val message = jsonObject.getString(ApiConstants.Response.MESSAGE)
    val data = jsonObject.getString(ApiConstants.Response.DATA)
    return ResponseObject<String>(status, message, data)

}

fun convertUserJsonToResponseObject(jsonObject: JSONObject): ResponseObject<User> {

    val status = jsonObject.getBoolean(ApiConstants.Response.STATUS)
    val message = jsonObject.getString(ApiConstants.Response.MESSAGE)

    val jsonData = JSONObject(jsonObject.getString(ApiConstants.Response.DATA))
    val data = jsonData.getUser()
    return ResponseObject<User>(status, message, data)
}
