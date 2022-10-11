package com.atom.android.bookshop.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atom.android.bookshop.R
import com.atom.android.bookshop.base.BaseActivity
import com.atom.android.bookshop.databinding.ActivitySplashBinding
import com.atom.android.bookshop.ui.main.MainActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun initView() {
        Timer().schedule(DELAY_TIME) {
            this@SplashActivity.runOnUiThread {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
    }


    override fun initData() {
        // late impl
    }

    override fun initEvent() {
        // late impl
    }

    companion object {
        private const val DELAY_TIME = 1000L
    }

}
