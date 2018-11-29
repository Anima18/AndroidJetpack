package com.chris.androidjetpack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chris.androidjetpack.databinding.DataBindingActivity
import com.chris.androidjetpack.databinding.ListViewDataBindingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toDataBindingActivity(view: View) {
        val intent = Intent(this, DataBindingActivity::class.java)
        startActivity(intent)
    }

    fun toDataBindingListActivity(view: View) {
        val intent = Intent(this, ListViewDataBindingActivity::class.java)
        startActivity(intent)
    }
}
