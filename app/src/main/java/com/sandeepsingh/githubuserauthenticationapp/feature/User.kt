package com.sandeepsingh.githubuserauthenticationapp.feature

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import android.util.Log
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.sandeepsingh.githubuserauthenticationapp.base.RetrofitServiceGenerator
import java.io.Serializable
import java.util.ArrayList

/**
 * Created by Sandeep on 9/29/18.
 */
@Entity(tableName = "user_table")
class User : Serializable {

    @SerializedName("login")
    @ColumnInfo(name = "user_name")
    var userName : String = ""

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var userId : Int = 0

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_image")
    var avatarImage : String = ""

    @SerializedName("type")
    @ColumnInfo(name = "user_type")
    var userType : String = ""

    @SerializedName("site_admin")
    @ColumnInfo(name = "is_user_admin")
    var isUserAdmin : Boolean = false

    companion object {
        fun getUserList(jsonElement: JsonElement?): MutableList<User> {
            var objectList: MutableList<User> = ArrayList<User>()
            try {
                if (null == jsonElement || jsonElement.isJsonNull)
                    throw NullPointerException("Null tagListElement not supported")
                if (!jsonElement.isJsonArray)
                    throw IllegalArgumentException("Provided JsonElement is not of type JsonArray")

                val listType = object : TypeToken<MutableList<User>>() {

                }.type

                objectList = RetrofitServiceGenerator.getGson().fromJson(jsonElement, listType)
            } catch (e: Exception) {

            }

            return objectList
        }
    }


}