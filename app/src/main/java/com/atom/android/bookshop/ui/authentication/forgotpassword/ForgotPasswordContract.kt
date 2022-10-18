package com.atom.android.bookshop.ui.authentication.forgotpassword

import android.content.Context
import com.atom.android.bookshop.base.BasePresenter
import com.atom.android.bookshop.ui.authentication.login.LoginContract

class ForgotPasswordContract {
    interface View {
        fun requestSuccess(message: String?)
        fun requestFailed(message: String?)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : BasePresenter<ForgotPasswordContract.View> {
        fun requestForgotPassword(context: Context?, email: String)
    }
}
