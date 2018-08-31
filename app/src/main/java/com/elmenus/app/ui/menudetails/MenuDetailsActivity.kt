package com.elmenus.app.ui.menudetails

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import com.elmenus.app.R
import com.elmenus.app.databinding.ActivityMenuDetailsBinding
import com.elmenus.app.model.Menu

class MenuDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_details)
        initData()


    }

    private fun initData() {
        var menu = intent.extras?.getSerializable("item") as Menu
        binding.model = menu
        var collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(
                R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = menu.name
        collapsingToolbarLayout.setExpandedTitleColor(
                resources.getColor(android.R.color.transparent))
    }
}
