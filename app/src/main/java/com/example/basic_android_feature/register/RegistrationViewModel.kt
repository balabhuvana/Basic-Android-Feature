package com.example.basic_android_feature.register

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.basic_android_feature.base.BaseViewModel
import com.example.basic_android_feature.model.UserInfo

class RegistrationViewModel(application: Application) : BaseViewModel(application) {

    private var userListData: LiveData<List<UserInfo>>? = null

    fun insertUserDataViaViewModel(userInfo: UserInfo) {
        userRepository?.registerPostReqForUserInfoAndInsertIntoRoom(userInfo)
    }

    fun selectUserList() {
        userListData = userRepository?.selectUserList()
    }

    fun observeUserList(): LiveData<List<UserInfo>>? {
        return userListData
    }
}