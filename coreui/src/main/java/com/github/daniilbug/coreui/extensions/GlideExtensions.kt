package com.github.daniilbug.coreui.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun ImageView.load(
    imageUrl: String?,
    builder: RequestBuilder<Drawable>.() -> Unit = { }
) {
    Glide.with(this)
        .load(imageUrl)
        .apply { builder() }
        .transition(withCrossFade())
        .into(this)
}

fun ImageView.load(
    @DrawableRes drawableId: Int,
    builder: RequestBuilder<Drawable>.() -> Unit = { }
) {
    Glide.with(this)
        .load(drawableId)
        .apply { builder() }
        .transition(withCrossFade())
        .into(this)
}