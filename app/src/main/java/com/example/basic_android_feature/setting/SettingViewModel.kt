package com.example.basic_android_feature.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.basic_android_feature.model.UserInfo
import com.example.basic_android_feature.repository.UserRepository
import com.example.basic_android_feature.room.UserDao
import com.example.basic_android_feature.room.UserRoomDatabase

class SettingViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository? = null
    private var userListData: LiveData<List<UserInfo>>? = null

    init {
        val userRoomDatabase: UserRoomDatabase =
            application.let { UserRoomDatabase.getDatabase(it) }
        val userDao: UserDao = userRoomDatabase.userDao()
        userRepository = UserRepository(userDao)
    }

    fun unRegisterUser() {
        userRepository?.unRegisterAllUser()
    }

    fun selectUserList() {
        userListData = userRepository?.selectUserList()
    }

    fun observeUserList(): LiveData<List<UserInfo>>? {
        return userListData
    }

}