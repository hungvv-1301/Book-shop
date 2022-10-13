package com.atom.android.bookshop.data.source.remote.api

import android.net.Uri
import com.atom.android.bookshop.BuildConfig

object ApiURL {

    fun pathLogin(): String = BuildConfig.API_HOST + ApiConstants.Endpoint.LOGIN

}
