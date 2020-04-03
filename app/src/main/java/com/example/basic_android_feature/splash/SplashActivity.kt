package com.example.basic_android_feature.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.basic_android_feature.R
import com.example.basic_android_feature.login.LoginActivity
import com.example.basic_android_feature.register.RegisterActivity

class SplashActivity : AppCompatActivity() {

    private var splashViewModel: SplashViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel!!.selectUserList()

        observeUserList()
    }

    private fun observeUserList() {
        splashViewModel?.observeUserList()?.observe(this, Observer { userInfoList ->
            if (userInfoList.isEmpty()) {
                startActivity(Intent(applicationContext, RegisterActivity::class.java))
            } else {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        })
    }

}
