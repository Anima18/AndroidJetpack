package com.chris.androidjetpack.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chris.androidjetpack.databinding.Follower
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import java.net.URL
import androidx.room.Room



/**
 * Created by jianjianhong on 18-12-20
 */
class TestRoomModel(application: Application): AndroidViewModel(application) {

    private lateinit var users: MutableLiveData<List<User>>

    fun getUsers(): LiveData<List<User>> {

        if (!::users.isInitialized) {
            users = MutableLiveData()
            searchUsers()
        }
        return users
    }

    fun addUser(firstName: String?, lastName: String?) {
        val db = AppDatabase.getAppDatabase(getApplication())
        db.userDao().insertAll(User(firstName, lastName))
        searchUsers()
    }

    public fun searchUsers() {
        doAsync {
            val db = AppDatabase.getAppDatabase(getApplication())
            users.postValue(db.userDao().all)
        }
    }

    private inline fun <reified T> genericType() = object: TypeToken<T>() {}.type

    override fun onCleared() {
        super.onCleared()
    }
}