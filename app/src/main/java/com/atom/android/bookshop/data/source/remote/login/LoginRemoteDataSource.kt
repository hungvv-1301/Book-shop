package com.atom.android.bookshop.data.source.remote.login

import com.atom.android.bookshop.data.model.LoginEntity
import com.atom.android.bookshop.data.source.ILoginDataSource
import com.atom.android.bookshop.data.source.remote.IRequestCallback
import com.atom.android.bookshop.data.source.remote.ResponseObject
import com.atom.android.bookshop.data.source.remote.api.ApiConstants
import com.atom.android.bookshop.data.source.remote.api.ApiURL
import com.atom.android.bookshop.data.source.remote.api.convertJsonToResponseObject
import com.atom.android.bookshop.utils.convertToJson
import com.atom.android.bookshop.utils.handler
import com.atom.android.bookshop.utils.JSONConvertException
import com.atom.android.bookshop.utils.httpConnection
import com.atom.android.bookshop.utils.remoteExecuteCallAPI
import org.json.JSONException
import org.json.JSONObject

class LoginRemoteDataSource : ILoginDataSource.Remote {

    override fun login(
        email: String,
        password: String,
        callback: IRequestCallback<ResponseObject<String>>
    ) {

        val dataFormLogin = LoginEntity(email, password).convertToJson()
        remoteExecuteCallAPI<ResponseObject<String>>(dataFormLogin, callback, ::getToken)
    }


    private fun getToken(
        dataFormLogin: String,
        callback: IRequestCallback<ResponseObject<String>>
    ): Unit {

        val jsonObjectResponseObject = JSONObject(
            httpConnection(
                dataFormLogin,
                ApiURL.pathLogin(),
                ApiConstants.Method.POST
            )
        )
        try {
            val responseObject = convertJsonToResponseObject(jsonObjectResponseObject)
            if (responseObject.status) {
                handler.post {
                    callback.onSuccess(responseObject)
                }
            } else {
                handler.post {
                    callback.onFailed(responseObject.message)
                }
            }
        } catch (ex: JSONException) {
            throw JSONConvertException(ex)
        } catch (ex: IllegalStateException) {
            throw JSONConvertException(ex)
        }
    }

    companion object {
        private var instance: LoginRemoteDataSource? = null
        fun getInstance(
        ) = synchronized(this) {
            instance ?: LoginRemoteDataSource().also { instance = it }
        }
    }

}
