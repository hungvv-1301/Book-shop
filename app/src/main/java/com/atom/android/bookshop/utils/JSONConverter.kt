package com.atom.android.bookshop.utils

import com.atom.android.bookshop.data.model.LoginEntity
import com.atom.android.bookshop.data.model.User
import org.json.JSONObject

fun LoginEntity.convertToJson(): String {
    val jsonObject = JSONObject()
    val strJson =
        jsonObject.put(LoginEntity.EMAIL, this.userEmail)
    jsonObject.put(LoginEntity.PASSWORD, this.userPassword)
    return strJson.toString()
}

fun JSONObject.getUser(): User {
    val confirmEmail = this.getInt(User.CONFIRM_EMAIL) ?: Constants.DEFAULT_INT
    val createdAt = convertStringToDate(this.getString(User.CREATED_AT) ?: Constants.DEFAULT_STRING)
    val dateOfBirth =
        convertStringToDate(this.getString(User.DATE_OF_BIRTH) ?: Constants.DEFAULT_STRING)
    val email = this.getString(User.EMAIL) ?: Constants.DEFAULT_STRING
    val gender = this.getString(User.GENDER) ?: Constants.DEFAULT_STRING
    val id = this.getInt(User.ID) ?: Constants.DEFAULT_INT
    val image = this.getString(User.IMAGE) ?: Constants.DEFAULT_STRING
    val name = this.getString(User.NAME) ?: Constants.DEFAULT_STRING
    val phone = this.getString(User.PHONE) ?: Constants.DEFAULT_STRING
    val updatedAt = convertStringToDate(this.getString(User.UPDATED_AT) ?: Constants.DEFAULT_STRING)
    return User(
        id,
        name,
        dateOfBirth,
        email,
        gender,
        image,
        phone,
        updatedAt,
        createdAt,
        confirmEmail == Constants.IS_TRUE
    )
}
