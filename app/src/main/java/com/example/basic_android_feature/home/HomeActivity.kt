package com.example.basic_android_feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basic_android_feature.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().replace(R.id.myHomeContainer, HomeFragment())
            .commit()
    }
}
