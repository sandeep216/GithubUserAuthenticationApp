package com.sandeepsingh.githubuserauthenticationapp.feature.userlist.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandeepsingh.githubuserauthenticationapp.R
import com.sandeepsingh.githubuserauthenticationapp.base.extension.loadFromUrl
import com.sandeepsingh.githubuserauthenticationapp.feature.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_user.*

/**
 * Created by Sandeep on 9/29/18.
 */
class UserAdapter(val context : Context, private var list: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UserViewHolder {
        val rootView = inflater.inflate(R.layout.layout_user,p0,false)
        return UserViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: UserViewHolder, p1: Int) {
        p0.onBindViewHolder(list[p1])
    }

    class  UserViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun onBindViewHolder(user: User){
            iv_user_avatar.loadFromUrl(user.avatarImage,100)
            tv_user_name.text = user.userName
            tv_user_id.text = "" + user.userId
        }
    }

    fun update(list: MutableList<User>){
        this.list = list
        notifyDataSetChanged()
    }
}