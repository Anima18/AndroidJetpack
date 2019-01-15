package com.chris.androidjetpack.room
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chris.androidjetpack.R
import com.chris.androidjetpack.databinding.ListviewUserBinding


class UserRecyclerViewAdapter(val items : List<User>) : androidx.recyclerview.widget.RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    interface RecyclerOnClick {
        fun onItemClick(view: View, position: Int)
    }

    var itemClick: RecyclerOnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listview_follower, parent, false)
        val listItemDatabinding = DataBindingUtil.inflate<ListviewUserBinding>(LayoutInflater.from(parent.context), R.layout.listview_user, parent, false)
        return ViewHolder(listItemDatabinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.user = items[position]
    }

    inner class ViewHolder(listItemDatabinding: ListviewUserBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(listItemDatabinding.root) {
        var itemBinding: ListviewUserBinding
        init {
            this.itemBinding = listItemDatabinding
            listItemDatabinding.root.setOnClickListener({view -> itemClick!!.onItemClick(itemView, layoutPosition)})
        }
    }
}