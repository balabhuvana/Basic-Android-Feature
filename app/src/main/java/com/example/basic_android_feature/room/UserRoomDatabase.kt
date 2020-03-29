package com.example.basic_android_feature.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basic_android_feature.model.UserInfo

@Database(
    entities = [UserInfo::class],
    version = 1,
    exportSchema = false
)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user_database_four"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}