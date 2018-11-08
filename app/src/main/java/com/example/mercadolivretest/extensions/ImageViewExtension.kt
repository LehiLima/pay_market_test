package com.example.mercadolivretest.extensions
import android.widget.ImageView
import com.bumptech.glide.Glide
// extension to load image into any imageView
fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}