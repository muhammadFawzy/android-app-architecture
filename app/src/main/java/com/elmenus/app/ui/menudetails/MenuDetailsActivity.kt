package com.elmenus.app.ui.menudetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.elmenus.app.R
import com.elmenus.app.model.Menu
import kotlinx.android.synthetic.main.activity_menu_details.*

class MenuDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_details)

        var menu=intent.extras?.getSerializable("item") as Menu

        Glide.with(this)
                .load(menu.photoUrl)
                .into(image)
    }
}
