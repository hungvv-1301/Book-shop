package com.atom.android.bookshop.ui.authentication.forgotpassword

import android.app.Dialog
import android.os.CountDownTimer
import androidx.navigation.Navigation
import com.atom.android.bookshop.R
import com.atom.android.bookshop.base.BaseFragment
import com.atom.android.bookshop.data.repository.ForgotPasswordRepository
import com.atom.android.bookshop.data.source.remote.forgotpassword.ForgotPasswordRemoteDataSource
import com.atom.android.bookshop.databinding.FragmentForgotPasswordBinding
import com.atom.android.bookshop.utils.Constants
import com.atom.android.bookshop.utils.navigateSafe
import com.atom.android.bookshop.utils.start
import com.atom.android.bookshop.utils.textSpannable
import com.atom.android.bookshop.utils.toast

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate),
    ForgotPasswordContract.View {

    private val forgotPresenter by lazy {
        ForgotPasswordPresenter.getInstance(
            ForgotPasswordRepository.getInstance(
                ForgotPasswordRemoteDataSource.getInstance()
            ),
            this
        )
    }

    private var countDownTimer: CountDownTimer? =
        object : CountDownTimer(Constants.TIME_REQUEST_FORGOT_PASS, Constants.SECOND_TO_MIL) {
            override fun onTick(millisUntilFinished: Long) {
                val messageWaitForSendRequest =
                    context?.getString(R.string.text_wait_for_send_request_forgot_password)
                val textSecond =
                    context?.getString(
                        R.string.text_second,
                        millisUntilFinished / Constants.SECOND_TO_MIL
                    )
                binding.textViewNotification.text = context?.textSpannable(
                    messageWaitForSendRequest,
                    ((textSecond ?: (millisUntilFinished / Constants.SECOND_TO_MIL))).toString()
                )
                binding.btnForgotPassword.isEnabled = false
            }

            override fun onFinish() {
                val messageRequireEmail =
                    context?.getString(R.string.text_suggest_forgot_password)
                binding.textViewNotification.text = messageRequireEmail
                binding.btnForgotPassword.isEnabled = true
            }
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
        binding.textViewLogin.setOnClickListener {
            navigate(R.id.action_navigation_forgot_password_to_navigation_login)
        }
        binding.btnForgotPassword.setOnClickListener {
            val email = binding.textInputLayoutEmail.editText?.text.toString()
            forgotPresenter.requestForgotPassword(context, email)
        }
    }

    override fun navigate(action: Int) {
        val navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment_activity_main
        )
        val popBackStack = navController.popBackStack()
        if (!popBackStack) {
            navController.navigateSafe(action)
        }
    }

    override fun requestSuccess(message: String?) {
        countDownTimer?.start()
        context?.toast(message)
    }

    override fun requestFailed(message: String?) {

        context?.toast(message)
    }

    override fun showProgressBar() {
        progessBar.start(true)
    }

    override fun hideProgressBar() {
        progessBar.dismiss()
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        countDownTimer = null
        super.onDestroy()
    }
}
