package com.atom.android.bookshop.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.atom.android.bookshop.data.repository.LoginRepository
import com.atom.android.bookshop.ui.authentication.login.LoginContract
import com.atom.android.bookshop.ui.authentication.login.LoginPresenter

class SharedPreferenceUtils(context: Context?) {

    fun putStringCommit(key: String, value: String?): Boolean {
        instance?.edit()?.let {
            it.putString(key, value)
            it.commit()
        }
        return false
    }

    fun putStringApply(key: String, value: String?) {
        instance?.edit()?.let {
            it.putString(key, value)
            it.apply()
        }
    }

    fun getString(key: String): String {
        instance?.let {
            it.getString(key, Constants.SHARED_PREF_DEFAULT_STRING)
        }
        return Constants.SHARED_PREF_DEFAULT_STRING
    }

    companion object {
        private var instance: SharedPreferences? = null
        fun getInstance(context: Context?) =
            synchronized(this) {
                instance ?: context?.getSharedPreferences(
                    Constants.SHARED_PREF,
                    Context.MODE_PRIVATE
                ).also {
                    instance = it
                }
            }
    }
}
