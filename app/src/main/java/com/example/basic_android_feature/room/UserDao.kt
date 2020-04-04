package com.example.basic_android_feature.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basic_android_feature.model.UserInfo

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserInfo?)

    @Query("SELECT * from user_table")
    fun selectAllUser(): LiveData<List<UserInfo>>

    @Query("DELETE FROM user_table")
    fun deleteAllUser()
}