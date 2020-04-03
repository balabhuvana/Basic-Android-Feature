package com.example.basic_android_feature.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basic_android_feature.R

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = LoginViewModel(application)

        supportFragmentManager.beginTransaction().replace(android.R.id.content, LoginFragment())
            .commit()
    }
}
