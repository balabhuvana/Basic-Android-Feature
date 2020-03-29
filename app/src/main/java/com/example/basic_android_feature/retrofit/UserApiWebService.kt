package com.example.basic_android_feature.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface UserApiWebService {
    @GET("api/users/2")
    fun getUser(): Call<UserModelRoot>
}