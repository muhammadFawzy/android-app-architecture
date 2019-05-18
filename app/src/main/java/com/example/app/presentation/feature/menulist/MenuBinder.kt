package com.example.app.presentation.feature.menulist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.app.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


/**
 * method for display corner image for imageView using glide.
 * @param url of image
 *
 */
@BindingAdapter("cornerUrl")
fun ImageView.setCornerImageUrl(url: String?) {
    val context = this.context
    if (url != null)
        if (!url.isEmpty()) {

            Glide.with(context).load(url)
                    .apply(RequestOptions().placeholder(R.drawable.food_placeholder))
                    .apply(RequestOptions().error(R.drawable.food_placeholder))
                    .apply(RequestOptions.bitmapTransform
                    (RoundedCornersTransformation(50, 10)))
                    .into(this)
        }
}

/**
 * method for display default image for imageView using glide.
 * @param url of image
 *
 */
@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    val context = this.context
    if (url != null)
        if (!url.isEmpty()) {

            Glide.with(context).load(url)
                    .into(this)
        }
}

