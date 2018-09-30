package com.sandeepsingh.githubuserauthenticationapp.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.sandeepsingh.githubuserauthenticationapp.base.dagger.AppComponent
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Sandeep on 9/29/18.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}