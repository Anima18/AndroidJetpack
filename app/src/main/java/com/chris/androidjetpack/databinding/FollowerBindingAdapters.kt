package com.chris.androidjetpack.databinding

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by jianjianhong on 18-11-29
 */

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url:String) {
    Picasso.get().load(url).into(view)
}