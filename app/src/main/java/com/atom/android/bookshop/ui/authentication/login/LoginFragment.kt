package com.atom.android.bookshop.ui.authentication.login

import android.app.Dialog
import androidx.navigation.Navigation
import com.atom.android.bookshop.R
import com.atom.android.bookshop.base.BaseFragment
import com.atom.android.bookshop.data.repository.LoginRepository
import com.atom.android.bookshop.data.source.local.LoginLocalDataSource
import com.atom.android.bookshop.data.source.remote.login.LoginRemoteDataSource
import com.atom.android.bookshop.databinding.FragmentLoginBinding
import com.atom.android.bookshop.utils.navigateSafe
import com.atom.android.bookshop.utils.start
import com.atom.android.bookshop.utils.toast


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate),
    LoginContract.View {

    private val loginPresenter by lazy {
        LoginPresenter.getInstance(
            LoginRepository.getInstance(
                LoginLocalDataSource.getInstance(),
                LoginRemoteDataSource.getInstance()
            ),
            this
        )
    }

    private val progessBar by lazy {
        Dialog(requireContext())
    }

    override fun initData() {
        // late impl
    }

    override fun initialize() {
        // late impl
    }

    override fun initView() {
        // late impl
    }

    override fun initEvent() {
        binding.textViewForgotPassword.setOnClickListener {
            navigate(R.id.action_navigation_login_to_navigation_forgot_password)
        }
        binding.btnLogin.setOnClickListener {
            progessBar.start(true)
            val email = binding.textInputLayoutEmail.editText?.text.toString()
            val password = binding.textInputLayoutPassword.editText?.text.toString()
            loginPresenter.login(context, email, password)
        }
    }

    override fun navigate(action: Int) {
        Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment_activity_main
        )
            .navigateSafe(action)
    }

    override fun loginSuccess(token: String?) {
        loginPresenter.saveToken(context, token)
        progessBar.dismiss()
        navigate(R.id.action_navigation_login_to_navigation_home)
    }

    override fun loginFailed(message: String?) {
        progessBar.dismiss()
        context?.toast(message)
    }

}
