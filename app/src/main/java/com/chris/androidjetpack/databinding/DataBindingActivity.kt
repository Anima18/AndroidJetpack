package com.chris.androidjetpack.databinding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.chris.androidjetpack.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_databinding.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by jianjianhong on 18-11-27
 */
class DataBindingActivity: AppCompatActivity() {
    val TAG: String = "DataBindingActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_databinding)

        dataBindingAct_recyclerView.layoutManager = LinearLayoutManager(this)

        doAsync {
            var data: List<Follower> = getRequestData("https://api.github.com/users/Anima18/followers")
            uiThread{
                Log.i(TAG, "running in mainThread")
                var adapter = RecyclerViewAdapter(data)
                adapter.itemClick = object: RecyclerViewAdapter.RecyclerOnClick {
                    override fun onItemClick(view: View, position: Int) {
                        toast(data[position].login)
                    }
                }
                //adapter.itemClick = object: RecyclerOnClick{(View, Int) ->  toast(data[position].login)}
                dataBindingAct_recyclerView.adapter = adapter
            }
        }
    }

    fun getRequestData(url: String): List<Follower> {
        var data: String = URL(url).readText()
        Log.i(TAG, data)
        val followerType = genericType<List<Follower>>()
        return Gson().fromJson<List<Follower>>(data, followerType)
    }

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type
}