package com.atom.android.bookshop.ui.authentication.forgotpassword

import androidx.navigation.Navigation
import com.atom.android.bookshop.R
import com.atom.android.bookshop.base.BaseFragment
import com.atom.android.bookshop.databinding.FragmentForgotPasswordBinding
import com.atom.android.bookshop.utils.navigateSafe

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate) {

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

}
