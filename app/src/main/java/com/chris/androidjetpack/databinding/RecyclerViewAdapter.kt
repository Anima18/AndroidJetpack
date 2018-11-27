package com.chris.kotlindemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chris.androidjetpack.R
import com.squareup.picasso.Picasso


class RecyclerViewAdapter(val items : List<Follower>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    interface RecyclerOnClick {
        fun onItemClick(view: View, position: Int)
    }

    var itemClick: RecyclerOnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listview_two_line_with_avatar, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.textView.text = login
            Picasso.get().load(avatar_url).into(holder.avatarView)
        }

    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val avatarView: ImageView
        val textView: TextView
        init {
            avatarView = itemView.findViewById(R.id.list_item_avatar)
            textView = itemView.findViewById(R.id.list_title_tv)

            itemView.setOnClickListener({view -> itemClick!!.onItemClick(itemView, layoutPosition)})
            //if(onItemClick != null) {}
        }
    }
}