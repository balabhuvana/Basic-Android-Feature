package com.example.basic_android_feature.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basic_android_feature.model.UserInfo
import com.example.basic_android_feature.room.UserDao

class UserRepository(private var userDao: UserDao) {

    private var userListData: LiveData<List<UserInfo>> =
        MutableLiveData()

    fun selectUserList(): LiveData<List<UserInfo>> {
        userListData = userDao.selectAllUser()
        return userListData
    }

}