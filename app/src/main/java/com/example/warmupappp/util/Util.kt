package com.example.warmupappp.util


import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.downloadFromURL(url: String) {
    Picasso.get().load(url).into(this)
}