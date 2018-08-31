package com.elmenus.app.ui.menulist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout.VERTICAL
import com.elmenus.app.R
import kotlinx.android.synthetic.main.activity_menus_list.*

class MenusListActivity : AppCompatActivity() {
    lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus_list)
        initData()


    }

    private fun initData() {
        adapter = MenuAdapter()
        recycler_menus.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recycler_menus.adapter = adapter

    }


}
