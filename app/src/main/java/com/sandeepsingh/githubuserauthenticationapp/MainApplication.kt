package com.sandeepsingh.githubuserauthenticationapp

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.sandeepsingh.githubuserauthenticationapp.base.dagger.AppComponent
import com.sandeepsingh.githubuserauthenticationapp.base.dagger.DaggerAppComponent
import com.sandeepsingh.githubuserauthenticationapp.base.dagger.RepoModule

/**
 * Created by Sandeep on 9/29/18.
 */
class MainApplication : Application() {

    lateinit var applicationComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        this.applicationComponent = DaggerAppComponent.builder()
                .repoModule(RepoModule(this))
                .build()

        Log.d("UserModel","In side application")
    }

}