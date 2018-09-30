package com.sandeepsingh.githubuserauthenticationapp.feature.userlist

import com.google.gson.JsonArray
import com.sandeepsingh.githubuserauthenticationapp.feature.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sandeep on 9/29/18.
 */
interface IUserRetrofit {
    @GET("users")
    fun getAllUsers(@Query("since") id : Int) : Call<JsonArray>
}