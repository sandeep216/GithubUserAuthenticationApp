package com.sandeepsingh.githubuserauthenticationapp.repo

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context
import com.sandeepsingh.githubuserauthenticationapp.feature.User


/**
 * Created by Sandeep on 9/29/18.
 */
@Database(entities = [(User ::class)], version = 1, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(UserRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                UserRoomDatabase::class.java, "user_database")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }


}