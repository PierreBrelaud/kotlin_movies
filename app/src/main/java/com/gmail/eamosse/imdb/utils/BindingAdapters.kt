package com.gmail.eamosse.imdb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.eamosse.imdb.R


@BindingAdapter(value = ["bindImage", "bindRounded"], requireAll = false)
fun bindImage(imageView: ImageView, url: String?, bindRounded: Boolean = false) {
    url?.let {
        Glide.with(imageView)
            .load("https://image.tmdb.org/t/p/original/$url")
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .apply(if (bindRounded) RequestOptions.circleCropTransform() else RequestOptions.noTransformation())
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}