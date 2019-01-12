package com.chris.androidjetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import com.chris.androidjetpack.databinding.DataBindingActivity
import com.chris.androidjetpack.databinding.ListViewDataBindingActivity
import com.chris.androidjetpack.lifecycle.LifecycleActivity
import com.chris.androidjetpack.notifications.NotificationActivity
import com.chris.androidjetpack.piging.PagerActivity
import com.chris.androidjetpack.viewmodel.ListViewDataBindingActivity2

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
        val intent = Intent(this, ListViewDataBindingActivity2::class.java)
        startActivity(intent)
    }

    fun toDLifecycleActivity(view: View) {
        val intent = Intent(this, LifecycleActivity::class.java)
        startActivity(intent)
    }

    fun toPagingActivity(view: View) {
        val intent = Intent(this, PagerActivity::class.java)
        startActivity(intent)
    }

    fun toNotificationActivity(view: View) {
        val intent = Intent(this, NotificationActivity::class.java)
        startActivity(intent)
    }
}
