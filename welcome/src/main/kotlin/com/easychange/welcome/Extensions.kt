package com.easychange.welcome

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun FragmentManager.addFragment(resourceId: Int, fragment: Fragment?) {
    beginTransaction().add(resourceId, fragment).commit()
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.load(imageResourceId: Int) {
    Picasso.with(context).load(imageResourceId).into(this)
}