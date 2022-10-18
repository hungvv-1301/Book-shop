package com.atom.android.bookshop.data.source.remote.api

import com.atom.android.bookshop.BuildConfig

object ApiURL {

    fun pathLogin(): String = BuildConfig.API_HOST + ApiConstants.Endpoint.LOGIN
    fun pathForgotEmail(): String = BuildConfig.API_HOST + ApiConstants.Endpoint.FORGOT_EMAIL

}
