package com.example.app.presentation.feature.menulist

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.app.R
import com.example.app.domain.entity.Menu
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.menu_item.*

class MenuHolder(override val containerView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(menu: Menu) {
        tvMenuName.text = menu.name

        Glide.with(containerView.context).load(menu.photoUrl)
                .apply(RequestOptions().placeholder(R.drawable.food_placeholder))
                .apply(RequestOptions().error(R.drawable.food_placeholder))
                .apply(RequestOptions.bitmapTransform
                (RoundedCornersTransformation(50, 10)))
                .into(ivImg)
    }
}