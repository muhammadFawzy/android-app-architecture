package com.example.app.presentation.feature.menudetails

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.app.R
import com.example.app.databinding.ActivityMenuDetailsBinding
import com.example.app.domain.entity.Menu
import com.google.android.material.appbar.CollapsingToolbarLayout

class MenuDetailsActivity : AppCompatActivity() {
    companion object {

        const val ITEM_EXTRA = "item"
    }

    private lateinit var binding: ActivityMenuDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_details)
        initData()
    }

    private fun initData() {
        val menu = intent.extras?.getSerializable(ITEM_EXTRA) as Menu
        binding.model = menu
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(
                R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = menu.name
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT)
    }
}
