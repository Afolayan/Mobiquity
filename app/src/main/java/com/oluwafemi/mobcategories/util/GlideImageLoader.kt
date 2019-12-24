package com.oluwafemi.mobcategories.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.oluwafemi.mobcategories.BuildConfig.BASE_URL
import com.oluwafemi.mobcategories.R

class GlideImageLoader : ImageLoader {

    override fun loadImage(url: String, view: ImageView) {
        val options = RequestOptions().apply {
            error(R.drawable.ic_cloud_error)
            placeholder(R.drawable.ic_cloud_error)
            diskCacheStrategy(DiskCacheStrategy.ALL)
        }
        val imageUrl = BASE_URL.plus(url)

        Glide.with(view)
            .load(imageUrl)
            .apply(options)
            .into(view)
    }


}
