package com.elmenus.app.ui.menulist

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import com.elmenus.app.R
import com.elmenus.app.model.Menu
import com.elmenus.app.ui.menudetails.MenuDetailsActivity
import kotlinx.android.synthetic.main.activity_menus_list.*


class MenusListActivity : AppCompatActivity(), ItemClickListener {

    lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus_list)
        initData()


    }

    private fun initData() {
        adapter = MenuAdapter(this)
        recycler_menus.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recycler_menus.adapter = adapter


    }

    override fun onItemClick(menu: Menu, shared: ImageView) {
        val intent = Intent(this, MenuDetailsActivity::class.java)
        intent.putExtra("item", menu)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this
                , shared as View, "menu_item")
        startActivity(intent, options.toBundle())

    }


}
