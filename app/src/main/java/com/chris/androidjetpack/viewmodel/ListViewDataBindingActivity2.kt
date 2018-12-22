package com.chris.androidjetpack.viewmodel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chris.androidjetpack.R
import com.chris.androidjetpack.databinding.Follower
import com.chris.androidjetpack.databinding.FollowerRecyclerViewAdapter
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
class ListViewDataBindingActivity2: AppCompatActivity() {
    val TAG: String = "ListViewDataBindingActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follower_list)

        dataBindingAct_recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val model = ViewModelProviders.of(this).get(FollowerModel::class.java);
        model.getFollowers().observe(this, Observer<List<Follower>> {
            var adapter = FollowerRecyclerViewAdapter(it)
            adapter.itemClick = object: FollowerRecyclerViewAdapter.RecyclerOnClick {
                override fun onItemClick(view: View, position: Int) {
                    toast(it[position].login)
                }
            }
            dataBindingAct_recyclerView.adapter = adapter
        })
    }

}