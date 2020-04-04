package com.example.basic_android_feature.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.basic_android_feature.model.UserInfo

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserInfo?)

    @Query("SELECT * from user_table where user_id = :userId LIMIT 1")
    fun selectSpecificUser(userId: Int): LiveData<UserInfo>

    @Query("SELECT * from user_table")
    fun selectAllUser(): LiveData<List<UserInfo>>

    @Query("DELETE FROM user_table")
    fun deleteAllUser()

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateUserLoginStatus(user: UserInfo?)
}