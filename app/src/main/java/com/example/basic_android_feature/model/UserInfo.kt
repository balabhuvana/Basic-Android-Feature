package com.example.basic_android_feature.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
class UserInfo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var uid = 0

    @ColumnInfo(name = "user_name")
    var userName: String = ""

    @ColumnInfo(name = "user_age")
    var userAge: String = ""

    @ColumnInfo(name = "user_location")
    var userPlace: String = ""

}