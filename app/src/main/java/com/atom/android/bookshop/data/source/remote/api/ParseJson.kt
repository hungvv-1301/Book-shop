package com.atom.android.bookshop.data.source.remote.api

import com.atom.android.bookshop.data.model.Bill
import com.atom.android.bookshop.data.model.User
import com.atom.android.bookshop.data.source.remote.ResponseObject
import com.atom.android.bookshop.utils.getBill
import com.atom.android.bookshop.utils.getUser
import org.json.JSONArray
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

fun convertBillJsonToResponseObject(jsonObject: JSONObject): ResponseObject<Bill> {

    val status = jsonObject.getBoolean(ApiConstants.Response.STATUS)
    val message = jsonObject.getString(ApiConstants.Response.MESSAGE)
    val jsonData = JSONObject(jsonObject.getString(ApiConstants.Response.DATA))
    val data = jsonData.getBill()
    return ResponseObject<Bill>(status, message, data)
}

fun convertBillsJsonToResponseObject(jsonObject: JSONObject): ResponseObject<List<Bill>> {
    val status = jsonObject.getBoolean(ApiConstants.Response.STATUS)
    val message = jsonObject.getString(ApiConstants.Response.MESSAGE)

    val jsonArray = JSONArray(jsonObject.getString(ApiConstants.Response.DATA))
    val bills = mutableListOf<Bill>()
    for (i in 0 until jsonArray.length()) {
        val jsonBill = jsonArray.getJSONObject(i)
        val bill = jsonBill.getBill()
        bills.add(bill)
    }
    return ResponseObject<List<Bill>>(status, message, bills)
}
