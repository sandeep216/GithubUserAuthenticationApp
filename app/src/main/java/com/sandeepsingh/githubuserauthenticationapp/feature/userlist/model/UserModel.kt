package com.sandeepsingh.githubuserauthenticationapp.feature.userlist.model


import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.google.gson.JsonArray
import com.sandeepsingh.githubuserauthenticationapp.base.prefs.PrefernceKeys
import com.sandeepsingh.githubuserauthenticationapp.base.prefs.Prefs
import com.sandeepsingh.githubuserauthenticationapp.feature.User
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.IUser
import com.sandeepsingh.githubuserauthenticationapp.feature.userlist.IUserRetrofit
import com.sandeepsingh.githubuserauthenticationapp.repo.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Sandeep on 9/29/18.
 */
class UserModel(val presenter: IUser.ModelToPresenter, val userRepository: UserRepository) : IUser.PresenterToModel {

    var listOfUser: MutableLiveData<MutableList<User>> = MutableLiveData()

    override fun loadData(isPaginating: Boolean) {
        try {
            if (listOfUser.value!!.size < 200) {
                presenter.showProgressIndicator(true)
                val userRetrofit = presenter.getRetrofitInstance().create(IUserRetrofit::class.java)
                val call = userRetrofit.getAllUsers(Prefs.getInt(presenter.getActivtiyContext(), PrefernceKeys.KEY_LAST_USER_ID_KEY, 0))
                val callback = object : Callback<JsonArray> {
                    override fun onFailure(call: Call<JsonArray>?, t: Throwable?) {
                        presenter.showProgressIndicator(false)
                        Toast.makeText(presenter.getActivtiyContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>?) {
                        if (response!!.isSuccessful) {
                            val userList = User.getUserList(response.body()!!)
                            listOfUser.value!!.addAll(userList)
                            listOfUser.postValue(listOfUser.value)
                            for (i in userList.indices) {
                                userRepository.insert(userList[i])
                                if (i == userList.indices.last) {
                                    Prefs.setInt(presenter.getActivtiyContext(), PrefernceKeys.KEY_LAST_USER_ID_KEY, userList[i].userId)
                                }
                            }
                        } else {
                            Toast.makeText(presenter.getActivtiyContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                        }

                        presenter.showProgressIndicator(false)
                    }
                }
                call.enqueue(callback)
            } else {
                listOfUser.value!!.addAll(userRepository.getAllUser())
                listOfUser.value = listOfUser.value
            }
        } catch (e: Exception) {
            Toast.makeText(presenter.getActivtiyContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun loadData() {
        try {
            if (userRepository.getAllUser().size == 0) {
                presenter.showProgressIndicator(true)
                val userRetrofit = presenter.getRetrofitInstance().create(IUserRetrofit::class.java)
                val call = userRetrofit.getAllUsers(0)
                val callback = object : Callback<JsonArray> {
                    override fun onFailure(call: Call<JsonArray>?, t: Throwable?) {
                        presenter.showProgressIndicator(false)
                        Toast.makeText(presenter.getActivtiyContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>?) {
                        if (response!!.isSuccessful) {
                            val userList = User.getUserList(response.body()!!)
                            listOfUser.postValue(userList)
                            for (i in userList.indices) {
                                userRepository.insert(userList[i])
                                if (i == userList.indices.last) {
                                    Prefs.setInt(presenter.getActivtiyContext(), PrefernceKeys.KEY_LAST_USER_ID_KEY, userList[i].userId)
                                }
                            }
                        } else {
                            Toast.makeText(presenter.getActivtiyContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                        }

                        presenter.showProgressIndicator(false)
                    }
                }
                call.enqueue(callback)
            } else {
                listOfUser.value = userRepository.getAllUser()
            }
        } catch (e: Exception) {
            Toast.makeText(presenter.getActivtiyContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun searchUser(query : String){
        try {
            if (query.isNotEmpty()) {
                val searchedUsers = userRepository.getAllUser().filter { it.userName.contains(query) }.toMutableList()
                listOfUser.value = searchedUsers
                if (searchedUsers.size == 0) {
                    Toast.makeText(presenter.getActivtiyContext(), "No User Found", Toast.LENGTH_SHORT).show()
                }
            } else {
                listOfUser.value = userRepository.getAllUser()
            }
        } catch (e : Exception){
            Toast.makeText(presenter.getActivtiyContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            listOfUser.value = userRepository.getAllUser()
        }
    }


    override fun getItemCount(): Int {
        return listOfUser.value!!.size
    }

    override fun getDisplayableItems(): MutableLiveData<MutableList<User>> {
        return listOfUser
    }

    override fun onDestroyInstance() {

    }
}