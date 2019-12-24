package com.oluwafemi.mobcategories.util

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun loadImage(url: String, view: ImageView)
}
