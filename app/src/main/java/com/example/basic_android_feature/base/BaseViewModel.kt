package com.example.basic_android_feature.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.basic_android_feature.repository.UserRepository
import com.example.basic_android_feature.room.UserDao
import com.example.basic_android_feature.room.UserRoomDatabase

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var userRepository: UserRepository? = null

    init {
        val userRoomDatabase: UserRoomDatabase =
            application.let { UserRoomDatabase.getDatabase(it) }
        val userDao: UserDao = userRoomDatabase.userDao()
        userRepository = UserRepository(userDao)
    }

}