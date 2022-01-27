package com.github.daniilbug.coreui.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

fun ImageView.load(
    imageUrl: String,
    builder: (RequestBuilder<Drawable>) -> RequestBuilder<Drawable> = { it }
) {
    Glide.with(this)
        .load(imageUrl)
        .run { builder(this) }
        .into(this)
}