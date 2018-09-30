package com.sandeepsingh.githubuserauthenticationapp.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sandeepsingh.githubuserauthenticationapp.feature.User

/**
 * Created by Sandeep on 9/29/18.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User) : Long

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * from user_table ORDER BY id ASC")
    fun getAllUsers(): MutableList<User>

    @Query("SELECT * from user_table WHERE user_name LIKE '%' || :search || '%'")
    fun getSearchedUser(search : String) : MutableList<User>
}