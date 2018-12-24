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

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.chris.androidjetpack.R
import com.chris.androidjetpack.databinding.CheeseItemBinding
import com.chris.androidjetpack.databinding.ListviewFollowerBinding
import com.chris.androidjetpack.piging.Cheese

/**
 * A simple ViewHolder that can bind a Cheese item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 */
class CheeseViewHolder(cheeseItemBinding :CheeseItemBinding) : RecyclerView.ViewHolder(cheeseItemBinding.root) {

    var itemBinding: CheeseItemBinding
    init {
        this.itemBinding = cheeseItemBinding
    }
}