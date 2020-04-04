package com.example.basic_android_feature.login

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.basic_android_feature.base.BaseViewModel
import com.example.basic_android_feature.model.UserInfo

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private var userListData: LiveData<List<UserInfo>>? = null

    private lateinit var userInfoLiveData: LiveData<UserInfo>

    fun selectUserList() {
        userListData = userRepository?.selectUserList()
    }

    fun observeUserList(): LiveData<List<UserInfo>>? {
        return userListData
    }

    fun updateUserLoginStatus(userInfo: UserInfo) {
        userRepository?.updateUserLoginStatusInRoom(userInfo)
    }

    fun selectSpecificUser(userId: Int) {
        userInfoLiveData = userRepository?.selectSpecificUserFromRoom(userId)!!
    }

    fun observeUserInfo(): LiveData<UserInfo> {
        return userInfoLiveData
    }
}