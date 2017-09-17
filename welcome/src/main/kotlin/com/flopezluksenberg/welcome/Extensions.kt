package com.flopezluksenberg.welcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.load(imageResourceId: Int) {
    Picasso.with(context).load(imageResourceId).into(this)
}