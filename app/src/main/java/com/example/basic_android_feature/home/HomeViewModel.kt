package com.example.basic_android_feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basic_android_feature.model.User

class HomeViewModel : ViewModel() {
    var liveDataUser: MutableLiveData<User> = MutableLiveData()

    fun setUserLiveData() {
        var user = User()
        user.age = 21
        user.name = "Arun"
        user.location = "Salem"
        liveDataUser.value = user
    }

    fun observeUserLiveData(): MutableLiveData<User> {
        return liveDataUser
    }
}