package com.atom.android.bookshop.data.source.remote.api

import com.atom.android.bookshop.data.source.remote.ResponseObject
import org.json.JSONObject

fun convertJsonToResponseObject(jsonObject: JSONObject): ResponseObject<String> {

    val status = jsonObject.getBoolean(ApiConstants.Response.STATUS)
    val message = jsonObject.getString(ApiConstants.Response.MESSAGE)
    val data = jsonObject.getString(ApiConstants.Response.DATA)
    return ResponseObject<String>(status, message, data)

}
