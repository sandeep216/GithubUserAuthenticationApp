package com.sandeepsingh.githubuserauthenticationapp.feature

import com.sandeepsingh.githubuserauthenticationapp.base.BaseActivity
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.view.UserListActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.sandeepsingh.githubuserauthenticationapp.R
import com.sandeepsingh.githubuserauthenticationapp.repo.UserRepository


/**
 * Created by Sandeep on 9/30/18.
 */
class SplashScreen : BaseActivity() {

    // Splash screen timer
    val SPLASH_TIME_OUT = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        UserRepository.getInstance(application)

        Handler().postDelayed( {
            val i = Intent(this@SplashScreen, UserListActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT)
    }
}