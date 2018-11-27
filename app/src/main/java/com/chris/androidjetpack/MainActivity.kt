package com.chris.androidjetpack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chris.androidjetpack.databinding.DataBindingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toDataBinding(view: View) {
        val intent = Intent(this, DataBindingActivity::class.java)
        startActivity(intent)
    }
}
