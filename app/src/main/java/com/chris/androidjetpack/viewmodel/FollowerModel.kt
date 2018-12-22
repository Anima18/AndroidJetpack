package com.chris.androidjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chris.androidjetpack.databinding.Follower
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import java.net.URL

/**
 * Created by jianjianhong on 18-12-20
 */
class FollowerModel: ViewModel() {

    private lateinit var users: MutableLiveData<List<Follower>>

    fun getFollowers(): LiveData<List<Follower>> {

        if (!::users.isInitialized) {
            users = MutableLiveData()
            loadUsers()
        }
        return users
    }

    private fun loadUsers() {
        doAsync {
            var data: String = URL("https://api.github.com/users/Anima18/followers").readText()
            val followerType = genericType<List<Follower>>()
            users.postValue( Gson().fromJson<List<Follower>>(data, followerType))
        }
    }

    private inline fun <reified T> genericType() = object: TypeToken<T>() {}.type

    override fun onCleared() {
        super.onCleared()
    }
}