package com.example.basic_android_feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basic_android_feature.R
import com.example.basic_android_feature.setting.SettingViewModel

class HomeActivity : AppCompatActivity() {

    lateinit var homeViewModel: HomeViewModel
    lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModel = HomeViewModel(application)
        settingViewModel = SettingViewModel(application)

        supportFragmentManager.beginTransaction().replace(R.id.myHomeContainer, HomeFragment())
            .commit()
    }
}
