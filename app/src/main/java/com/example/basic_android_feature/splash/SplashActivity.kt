package com.example.basic_android_feature.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.basic_android_feature.R
import com.example.basic_android_feature.home.HomeActivity
import com.example.basic_android_feature.util.ConstantUtil

class SplashActivity : AppCompatActivity() {

    private val mHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        launchScreen()
    }

    private fun launchScreen() {
        mHandler.postDelayed(Runnable {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, ConstantUtil.WAIT_FOR_DELAY)
    }

    override fun onDestroy() {
        mHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
