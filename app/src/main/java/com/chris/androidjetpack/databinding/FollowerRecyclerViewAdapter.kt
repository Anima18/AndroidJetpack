package com.chris.androidjetpack.databinding
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chris.androidjetpack.R


class FollowerRecyclerViewAdapter(val items : List<Follower>) : RecyclerView.Adapter<FollowerRecyclerViewAdapter.ViewHolder>() {

    interface RecyclerOnClick {
        fun onItemClick(view: View, position: Int)
    }

    var itemClick: RecyclerOnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listview_follower, parent, false)
        val listItemDatabinding = DataBindingUtil.inflate<ListviewFollowerBinding>(LayoutInflater.from(parent.context), R.layout.listview_follower, parent, false)
        return ViewHolder(listItemDatabinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.follower = items[position]
    }

    inner class ViewHolder(listItemDatabinding: ListviewFollowerBinding) : RecyclerView.ViewHolder(listItemDatabinding.root) {
        var itemBinding: ListviewFollowerBinding
        init {
            this.itemBinding = listItemDatabinding
            listItemDatabinding.root.setOnClickListener({view -> itemClick!!.onItemClick(itemView, layoutPosition)})
        }
    }
}