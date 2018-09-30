package com.sandeepsingh.githubuserauthenticationapp.feature.userlist.presenter

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.sandeepsingh.githubuserauthenticationapp.base.BasePresenter
import com.sandeepsingh.githubuserauthenticationapp.feature.User
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.IUser
import retrofit2.Retrofit

/**
 * Created by Sandeep on 9/29/18.
 */
class UserPresenter(view: IUser.PresenterToView) : BasePresenter<IUser.PresenterToView>(view), IUser.ModelToPresenter, IUser.ViewToPresenter {

    private var model: IUser.PresenterToModel? = null

    override fun getUserData(): MutableLiveData<MutableList<User>> {
        return model!!.getDisplayableItems()
    }

    override fun notifyDataSetChanged(list: MutableLiveData<MutableList<User>>) {
        getView()!!.notifyDataSetChanged(list)
    }

    override fun getRetrofitInstance(): Retrofit {
        return getView()!!.getRetrofitInstance()
    }

    override fun loadData(isPaginating : Boolean) {
        model!!.loadData(isPaginating)
    }

    override fun loadData() {
        model!!.loadData()
    }

    override fun searchUser(query : String){
        model!!.searchUser(query)
    }

    override fun setModel(model: IUser.PresenterToModel) {
        this.model = model
    }

    override fun setViewReference(view: IUser.PresenterToView) {
        setView(view)
    }

    override fun showProgressIndicator(show: Boolean) {
        getView()!!.showProgressIndicator(show)
    }

    override fun getAppContext(): Context {
        return getView()!!.getAppContext()
    }

    override fun getActivtiyContext(): Context {
        return getView()!!.getActivtiyContext()
    }

    override fun onDestroyInstance() {
       model!!.onDestroyInstance()
    }

}