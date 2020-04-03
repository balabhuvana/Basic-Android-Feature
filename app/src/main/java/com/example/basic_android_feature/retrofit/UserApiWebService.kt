package com.example.basic_android_feature.retrofit

import com.example.basic_android_feature.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiWebService {
    @GET("api/users/2")
    fun getUser(): Call<UserModelRoot>

    @POST("api/users")
    fun postUserInfo(
        @Body
        userInfo: UserInfo
    ): Call<UserInfo>
}