package com.chris.androidjetpack.databinding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chris.androidjetpack.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_follower_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by jianjianhong on 18-11-27
 */
class ListViewDataBindingActivity: AppCompatActivity() {
    val TAG: String = "ListViewDataBindingActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follower_list)

        dataBindingAct_recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        doAsync {
            var data: List<Follower> = getRequestData("https://api.github.com/users/Anima18/followers")
            uiThread{
                var adapter = FollowerRecyclerViewAdapter(data)
                adapter.itemClick = object: FollowerRecyclerViewAdapter.RecyclerOnClick {
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
        val followerType = genericType<List<Follower>>()
        return Gson().fromJson<List<Follower>>(data, followerType)
    }

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type
}