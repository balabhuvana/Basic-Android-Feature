package com.example.basic_android_feature.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basic_android_feature.R

class RegisterActivity : AppCompatActivity() {

    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registrationViewModel = RegistrationViewModel(application)

        supportFragmentManager.beginTransaction().replace(android.R.id.content, RegisterFragment())
            .commit()
    }
}
