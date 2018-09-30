package com.sandeepsingh.githubuserauthenticationapp.feature.userlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.sandeepsingh.githubuserauthenticationapp.feature.User
import retrofit2.Retrofit

/**
 * Created by Sandeep on 9/29/18.
 */
interface IUser {

    interface ViewToPresenter{
        fun loadData()
        fun loadData(isPaginating: Boolean)
        fun setModel(model: IUser.PresenterToModel)
        fun setViewReference(view: IUser.PresenterToView)
        fun onDestroyInstance()
        fun searchUser(query: String)
    }

    interface PresenterToModel{
        fun loadData()
        fun loadData(isPaginating : Boolean)
        fun getItemCount(): Int
        fun getDisplayableItems(): MutableLiveData<MutableList<User>>
        fun onDestroyInstance()
        fun searchUser(query: String)
    }

    interface ModelToPresenter{
        fun showProgressIndicator(show: Boolean)
        fun notifyDataSetChanged(list: MutableLiveData<MutableList<User>>)
        fun getRetrofitInstance() : Retrofit
        fun getUserData() : MutableLiveData<MutableList<User>>
        fun getAppContext() : Context
        fun getActivtiyContext() : Context
    }

    interface PresenterToView{
        fun showProgressIndicator(show: Boolean)
        fun notifyDataSetChanged(list: MutableLiveData<MutableList<User>>)
        fun getRetrofitInstance() : Retrofit
        fun getAppContext() : Context
        fun getActivtiyContext() : Context
    }
}