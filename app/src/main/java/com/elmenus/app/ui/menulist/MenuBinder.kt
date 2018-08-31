package com.elmenus.app.ui.menulist

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Created by mf4wzy on 06/01/18.
 */


@BindingAdapter("cornerUrl")
fun ImageView.setCornerImageUrl(url: String?) {
    val context = this.context
    if (url != null)
        if (!url.isEmpty()) {

            Glide.with(context).load(url)
                    .apply(RequestOptions.bitmapTransform
                    (RoundedCornersTransformation(50, 10)))
                    .into(this)
        }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    val context = this.context
    if (url != null)
        if (!url.isEmpty()) {

            Glide.with(context).load(url)
                    .into(this)
        }
}

