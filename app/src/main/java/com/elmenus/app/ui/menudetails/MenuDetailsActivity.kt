package com.elmenus.app.ui.menudetails

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import com.elmenus.app.R
import com.elmenus.app.databinding.ActivityMenuDetailsBinding
import com.elmenus.app.model.Menu

class MenuDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_details)
        initData()


    }

    private fun initData() {
        val menu = intent.extras?.getSerializable("item") as Menu
        binding.model = menu
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(
                R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = menu.name
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT)
    }
}
