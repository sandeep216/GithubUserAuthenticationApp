package com.sandeepsingh.githubuserauthenticationapp.base.dagger

import android.content.Context
import com.sandeepsingh.githubuserauthenticationapp.MainApplication
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.view.UserListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sandeep on 9/29/18.
 */

@Component(modules = [(RepoModule :: class)])
@Singleton
abstract class AppComponent {

    abstract fun inject(mainApplication: MainApplication)
    abstract fun inject(userListActivity: UserListActivity)

    companion object {
        fun from(context: Context): AppComponent? {
            return (context.applicationContext as MainApplication).applicationComponent
        }
    }
}