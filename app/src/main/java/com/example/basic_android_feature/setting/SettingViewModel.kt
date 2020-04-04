package com.example.basic_android_feature.setting

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.basic_android_feature.base.BaseViewModel
import com.example.basic_android_feature.model.UserInfo

class SettingViewModel(application: Application) : BaseViewModel(application) {

    private var userListData: LiveData<List<UserInfo>>? = null


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