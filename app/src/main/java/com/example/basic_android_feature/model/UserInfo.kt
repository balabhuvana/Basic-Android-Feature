package com.example.basic_android_feature.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_table")
class UserInfo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var uid = 0

    @SerializedName("name")
    @ColumnInfo(name = "user_name")
    var userName: String = ""

    @SerializedName("job")
    @ColumnInfo(name = "user_job")
    var userJob: String = ""

    @SerializedName("id")
    @ColumnInfo(name = "user_net_id")
    var userNetId: String = ""

    @SerializedName("createdAt")
    @ColumnInfo(name = "user_created_at")
    var userCreatedAt: String = ""

}