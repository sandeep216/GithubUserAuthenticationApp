package com.sandeepsingh.githubuserauthenticationapp.feature.userlist.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.sandeepsingh.githubuserauthenticationapp.R
import com.sandeepsingh.githubuserauthenticationapp.base.BaseActivity
import com.sandeepsingh.githubuserauthenticationapp.base.dagger.AppComponent
import com.sandeepsingh.githubuserauthenticationapp.base.dagger.RepoModule
import com.sandeepsingh.githubuserauthenticationapp.base.dagger.RepoModule_GetRetrofitFactory
import com.sandeepsingh.githubuserauthenticationapp.feature.User
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.IUser
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.adapter.UserAdapter
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.model.UserModel
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.presenter.UserPresenter
import com.sandeepsingh.githubuserauthenticationapp.repo.UserRepository
import kotlinx.android.synthetic.main.activity_user_list.*
import retrofit2.Retrofit

class UserListActivity : BaseActivity(), IUser.PresenterToView {

    lateinit var mPresenter: UserPresenter

    lateinit var mAdapter: UserAdapter

    lateinit var retroFit: Retrofit

    lateinit var userRepo: UserRepository

    lateinit var linearLayoutManager: LinearLayoutManager

    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setUpMvp()
        initRecyclerView()
        initView()
        AppComponent.from(this)!!.inject(this)
        retroFit = RepoModule_GetRetrofitFactory.proxyGetRetrofit(RepoModule(application))
        mPresenter.loadData()
    }

    fun setUpMvp() {
        userRepo = UserRepository.getInstance(application)
        mPresenter = UserPresenter(this)
        mPresenter.setViewReference(this)
        val model = UserModel(mPresenter, userRepo)
        mPresenter.setModel(model)
    }

    fun initRecyclerView() {
        mAdapter = UserAdapter(this, mutableListOf())
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = mAdapter

        mPresenter.getUserData().observe(this, Observer {
            if (it != null) {
                mAdapter.update(it)
                isLoading = false
            }
        })

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (UserRepository.getInstance(application).getAllUser().size < 200) {
                    val visibleItemCount = linearLayoutManager.childCount
                    val totalItemCount = linearLayoutManager.itemCount
                    val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                        mPresenter.loadData(true)
                        isLoading = true
                    }
                }
            }
        })

    }

    fun initView(){
        tiet_search.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mPresenter.searchUser(s.toString())
            }

        })
    }

    override fun showProgressIndicator(show: Boolean) {
        if (show) {
            loader.visibility = View.VISIBLE
        } else {
            loader.visibility = View.GONE
        }
    }

    override fun notifyDataSetChanged(list: MutableLiveData<MutableList<User>>) {
        if (list.value != null) {
            mAdapter.update(list.value!!)
        }
    }

    override fun getRetrofitInstance(): Retrofit {
        return retroFit
    }

    override fun getAppContext(): Context {
        return applicationContext
    }

    override fun getActivtiyContext(): Context {
        return this
    }
}
