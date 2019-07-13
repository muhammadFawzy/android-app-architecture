package com.example.app.presentation.feature.menulist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data.source.remote.MenusApi
import com.example.app.data.source.remote.RetrofitClient
import com.example.app.domain.entity.Menu
import com.example.app.domain.entity.MenuList
import com.example.app.presentation.feature.menudetails.MenuDetailsActivity
import kotlinx.android.synthetic.main.activity_menus_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NonTestableActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var adapter: MenuAdapter
    var lastPageNo: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus_list)
        setupRecycler()
        getMenus()
    }

    private fun setupRecycler() {
        adapter = MenuAdapter(this)
        recycler_menus.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_menus.adapter = adapter
    }

    private fun getMenus() {
        RetrofitClient.client.create(MenusApi::class.java)
                .getItems(lastPageNo).enqueue(object : Callback<MenuList> {
                    override fun onResponse(call: Call<MenuList>, response: Response<MenuList>) {
                        response.body()?.let {
                            val menusWithPage = mapOf(lastPageNo to response.body()!!.items)
                            adapter.addAll(it.items, menusWithPage.keys.first())
                            lastPageNo++
                        }
                    }

                    override fun onFailure(call: Call<MenuList>, t: Throwable) {
                        tvNetworkError.visibility = View.VISIBLE
                    }

                })
    }


    override fun onItemClick(menu: Menu, shared: ImageView) {
        val intent = Intent(this, MenuDetailsActivity::class.java)
        intent.putExtra(MenuDetailsActivity.ITEM_EXTRA, menu)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this
                , shared as View, "menu_item")
        startActivity(intent, options.toBundle())
    }


}
