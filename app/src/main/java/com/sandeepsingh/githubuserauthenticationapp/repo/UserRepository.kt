package com.sandeepsingh.githubuserauthenticationapp.repo

import android.app.Application
import android.os.AsyncTask
import com.sandeepsingh.githubuserauthenticationapp.feature.User


/**
 * Created by Sandeep on 9/29/18.
 */
class UserRepository(application: Application) {

    var mUserDao: UserDao
    var mAllUser: MutableList<User> = mutableListOf()

    init {
        val db = UserRoomDatabase.getDatabase(application)
        mUserDao = db!!.userDao()
        Thread{
            run{
                mAllUser =  mUserDao.getAllUsers()
            }
        }.start()
    }

    companion object {
        var mUserRepoInstance : UserRepository ?= null

        fun getInstance(application: Application) : UserRepository{
            if (mUserRepoInstance == null){
                mUserRepoInstance = UserRepository(application)
            }

            return mUserRepoInstance!!
        }
    }

    fun getAllUser(): MutableList<User> {
        return mAllUser
    }


    fun insert(user: User) {
         InsertAsyncTask(mUserDao).execute(user)
    }

    private class InsertAsyncTask internal constructor(val mAsyncTaskDao: UserDao) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg params: User): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}