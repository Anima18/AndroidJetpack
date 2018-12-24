/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package paging.android.example.com.pagingsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chris.androidjetpack.R
import com.chris.androidjetpack.databinding.CheeseItemBinding
import com.chris.androidjetpack.databinding.ListviewFollowerBinding
import com.chris.androidjetpack.piging.Cheese
import com.chris.androidjetpack.piging.NetworkState
import com.chris.androidjetpack.piging.NetworkStateItemViewHolder

/**
 * A simple PagedListAdapter that binds Cheese items into CardViews.
 * <p>
 * PagedListAdapter is a RecyclerView.Adapter base class which can present the content of PagedLists
 * in a RecyclerView. It requests new pages as the user scrolls, and handles new PagedLists by
 * computing list differences on a background thread, and dispatching minimal, efficient updates to
 * the RecyclerView to ensure minimal UI thread work.
 * <p>
 * If you want to use your own Adapter base class, try using a PagedListAdapterHelper inside your
 * adapter instead.
 *
 * @see android.arch.paging.PagedListAdapter
 * @see android.arch.paging.AsyncPagedListDiffer
 */
class CheeseAdapter(private val retryCallback: () -> Unit) : PagedListAdapter<Cheese, RecyclerView.ViewHolder>(diffCallback) {

    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.bindTo(getItem(position))
        when (getItemViewType(position)) {
            R.layout.cheese_item -> {
                (holder as CheeseViewHolder).itemBinding.cheese = getItem(position)
            }
            R.layout.network_state_item -> (holder as NetworkStateItemViewHolder).bindTo(
                networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.cheese_item -> {
                val listItemDatabinding = DataBindingUtil.inflate<CheeseItemBinding>(LayoutInflater.from(parent.context), R.layout.cheese_item, parent, false)

                CheeseViewHolder(listItemDatabinding)
            }
            R.layout.network_state_item -> NetworkStateItemViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.cheese_item
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }

    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see android.support.v7.util.DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Cheese>() {
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                    oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                    oldItem == newItem
        }
    }
}
