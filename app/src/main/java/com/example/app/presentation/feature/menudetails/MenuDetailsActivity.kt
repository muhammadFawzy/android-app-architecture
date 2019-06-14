package com.example.app.presentation.feature.menudetails

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app.R
import com.example.app.domain.entity.Menu
import kotlinx.android.synthetic.main.activity_menu_details.*

class MenuDetailsActivity : AppCompatActivity() {
    companion object {
        const val ITEM_EXTRA = "item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_details)
        initData()
    }

    private fun initData() {
        val menu = intent.extras?.getSerializable(ITEM_EXTRA) as Menu
        configureToolbar(menu)

        Glide.with(this).load(menu.photoUrl)
                .into(ivCoverImage)

        tvDescription.text = menu.description
    }

    private fun configureToolbar(menu: Menu) {
        collapsing_toolbar.title = menu.name
        collapsing_toolbar.setExpandedTitleColor(Color.TRANSPARENT)
    }
}
