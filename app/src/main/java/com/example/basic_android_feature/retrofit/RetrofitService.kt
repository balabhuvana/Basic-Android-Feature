package com.example.basic_android_feature.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://reqres.in/"

    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    val userApi: UserApiWebService
        get() {

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create(UserApiWebService::class.java)
        }
}