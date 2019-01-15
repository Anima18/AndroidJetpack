package com.chris.androidjetpack.room

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.chris.androidjetpack.MainActivity
import com.chris.androidjetpack.R
import com.chris.androidjetpack.databinding.Follower
import com.chris.androidjetpack.databinding.FollowerRecyclerViewAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_follower_list.*
import kotlinx.android.synthetic.main.activity_room_test.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by jianjianhong on 18-11-27
 */
class TestRoomActivity: AppCompatActivity() {
    val TAG: String = "TestRoomActivity"
    var model: TestRoomModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_test)

        testRoomAct_recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        model = ViewModelProviders.of(this).get(TestRoomModel::class.java);
        model!!.getUsers().observe(this, Observer<List<User>> {
            var adapter = UserRecyclerViewAdapter(it)
            adapter.itemClick = object: UserRecyclerViewAdapter.RecyclerOnClick {
                override fun onItemClick(view: View, position: Int) {
                    toast(it[position].firstName)
                }
            }
            testRoomAct_recyclerView.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_test_room, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.add) {
            MaterialDialog.Builder(this)
                .title("Title")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("name", null, {dialog: MaterialDialog, input: CharSequence? ->
                    var names = input!!.split(" ")
                    model!!.addUser(names[0], names[1])
                })
                .positiveText("确定")
                .show()
        }else if(item?.itemId == R.id.search) {
            model!!.searchUsers()
        }
        return super.onOptionsItemSelected(item)
    }
}

