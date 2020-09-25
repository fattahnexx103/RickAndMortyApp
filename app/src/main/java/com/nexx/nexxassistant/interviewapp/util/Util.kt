package com.nexx.nexxassistant.interviewapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nexx.nexxassistant.interviewapp.R

//Use kotlin extension function to add loadImage functionality to imageview
//Use glid to load image into ImageView.
fun ImageView.loadImage(uri: String?){
        val options = RequestOptions().error(R.mipmap.ic_launcher_round) //if there is no image to grab, get the ic launcher icon as image
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
}