package com.example.basic_android_feature.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basic_android_feature.model.UserInfo
import com.example.basic_android_feature.retrofit.RetrofitService
import com.example.basic_android_feature.room.UserDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private var userDao: UserDao) {

    private var userListData: LiveData<List<UserInfo>> =
        MutableLiveData()

    private lateinit var userInfo: LiveData<UserInfo>

    private var retrofitService = RetrofitService()

    fun selectUserList(): LiveData<List<UserInfo>> {
        userListData = userDao.selectAllUser()
        return userListData
    }

    fun registerPostReqForUserInfoAndInsertIntoRoom(userInfo: UserInfo) {
        retrofitService.userApi.postUserInfo(userInfo).enqueue(object : Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.i("----> ", "" + t.message)
            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val userInfo: UserInfo? = response.body()
                Log.i("----> ", "Name : " + userInfo?.userName)
                Log.i("----> ", "Job : " + userInfo?.userJob)
                Log.i("----> ", "Id : " + userInfo?.userNetId)
                Log.i("----> ", "Created At : " + userInfo?.userCreatedAt)
                GlobalScope.launch {
                    userDao.insertUser(userInfo)
                }
            }
        })
    }

    fun unRegisterAllUser() {
        GlobalScope.launch {
            userDao.deleteAllUser()
        }
    }

    fun updateUserLoginStatusInRoom(userInfo: UserInfo) {
        GlobalScope.launch {
            userDao.updateUserLoginStatus(userInfo)
        }
    }

    fun selectSpecificUserFromRoom(userId: Int): LiveData<UserInfo> {
        userInfo = userDao.selectSpecificUser(userId)
        return userInfo
    }

}