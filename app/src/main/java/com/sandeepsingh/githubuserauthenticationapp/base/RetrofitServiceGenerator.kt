package com.sandeepsingh.githubuserauthenticationapp.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by Sandeep on 9/29/18.
 */
class RetrofitServiceGenerator {

    companion object {
        private val _gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()

        fun getGson(): Gson {
            return _gson
        }
    }
}