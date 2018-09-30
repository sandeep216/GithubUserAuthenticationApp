package com.sandeepsingh.githubuserauthenticationapp.base.dagger

import android.app.Application
import android.provider.Settings.System.DATE_FORMAT
import com.google.gson.GsonBuilder
import com.sandeepsingh.githubuserauthenticationapp.base.extension.empty
import com.sandeepsingh.githubuserauthenticationapp.base.prefs.PrefernceKeys
import com.sandeepsingh.githubuserauthenticationapp.base.prefs.Prefs
import com.sandeepsingh.githubuserauthenticationapp.base.prefs.URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Sandeep on 9/29/18.
 */
@Module()
class RepoModule(val application: Application) {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val _gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()

        val httpClient = OkHttpClient.Builder()
        httpClient
                .addInterceptor { chain ->
                    val authKey: String = Prefs.getString(application, PrefernceKeys.KEY_ACCESS_TOKEN, String.empty())

                    val request = chain.request().newBuilder().addHeader("Authorization", authKey).build()
                    val response = chain.proceed(request)
                    response
                }.connectTimeout(8, TimeUnit.SECONDS).readTimeout(8, TimeUnit.SECONDS)

        val builder = Retrofit.Builder().baseUrl(URL.GITHUB_API).addConverterFactory(GsonConverterFactory.create(_gson))
        val client = httpClient.build()
        return builder.client(client).build()
    }
}