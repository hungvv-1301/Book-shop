package com.atom.android.bookshop.ui.main


import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.atom.android.bookshop.R
import com.atom.android.bookshop.base.BaseActivity
import com.atom.android.bookshop.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun initView() {
        setUpNav()
    }

    override fun initData() {
        // late impl
    }

    override fun initEvent() {
        // late impl
    }

    private fun setUpNav() {
        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
        NavigationUI.setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navigation_login -> setNavigation(View.GONE)
                R.id.navigation_forgot_password -> setNavigation(View.GONE)
                else -> setNavigation(View.VISIBLE)
            }
        }
    }

    private fun setNavigation(mode: Int) {
        binding.navView.visibility = mode
    }
}
