package com.chris.androidjetpack.piging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chris.androidjetpack.R
import kotlinx.android.synthetic.main.activity_paging.*
import paging.android.example.com.pagingsample.CheeseAdapter
import paging.android.example.com.pagingsample.CheeseViewModel

/**
 * Created by jianjianhong on 18-12-22
 */
class PagerActivity: AppCompatActivity() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(CheeseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_paging)

        val listData = viewModel.allCheeses()

        // Create adapter for the RecyclerView
        val adapter = CheeseAdapter(listData.retry)
        cheeseList.adapter = adapter


        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        listData.pagedList.observe(this, Observer(adapter::submitList))

        listData.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })


    }


}