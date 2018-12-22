package com.chris.androidjetpack.databinding

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chris.androidjetpack.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import java.net.URL

/**
 * Created by jianjianhong on 18-11-29
 */
class DataBindingActivity: AppCompatActivity() {
    var my: Me? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bind: ActivityMeBinding = DataBindingUtil.setContentView(this, R.layout.activity_me)
        my = Me("Anima18", "https://avatars2.githubusercontent.com/u/11849145?v=4", "zhuhai", "heloo world")
        bind.me = my

        my!!.login = "hello"
    }

    fun getRequestData(url: String): Me {
        var data: String = URL(url).readText()
        val followerType = genericType<Me>()
        return Gson().fromJson<Me>(data, followerType)
    }

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type
}