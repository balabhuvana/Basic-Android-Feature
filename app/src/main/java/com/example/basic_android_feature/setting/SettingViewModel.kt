package com.example.basic_android_feature.setting

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.basic_android_feature.base.BaseViewModel
import com.example.basic_android_feature.model.UserInfo

class SettingViewModel(application: Application) : BaseViewModel(application) {

    private var userListData: LiveData<List<UserInfo>>? = null

    private lateinit var userInfoLiveData: LiveData<UserInfo>

    fun unRegisterUser() {
        userRepository?.unRegisterAllUser()
    }

    fun userLogout(userInfo: UserInfo) {
        userInfo.isUserLogined = false
        userRepository?.updateUserLoginStatusInRoom(userInfo)
    }

    fun selectUserList() {
        userListData = userRepository?.selectUserList()
    }

    fun selectSpecificUser(userId: Int) {
        userInfoLiveData = userRepository?.selectSpecificUserFromRoom(userId)!!
    }

    fun observeUserList(): LiveData<List<UserInfo>>? {
        return userListData
    }

    fun observeUserInfo(): LiveData<UserInfo> {
        return userInfoLiveData
    }

}