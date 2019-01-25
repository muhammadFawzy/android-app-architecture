package com.example.app.presentation.feature.menulist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import com.example.app.R
import com.example.app.data.source.local.MenuDB
import com.example.app.databinding.ActivityMenusListBinding
import com.example.app.domain.entity.Menu
import com.example.app.presentation.feature.menudetails.MenuDetailsActivity
import com.example.app.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_menus_list.*


class MenusListActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var adapter: MenuAdapter
    private lateinit var binding: ActivityMenusListBinding
    lateinit var viewModel: MenuViewModel
    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menus_list)
        initData()
        initListeners()
        initObservables()
    }


    private fun initData() {
        // creating DB
        val menuDb = MenuDB.getInMemoryDatabase(this)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(menuDb)).get(MenuViewModel::class.java)
        binding.viewmodel = viewModel

        adapter = MenuAdapter(this)
        recycler_menus.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recycler_menus.adapter = adapter
    }

    private fun initListeners() {
        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(recycler_menus.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getMenus(page)

            }
        }

        recycler_menus.addOnScrollListener(endlessRecyclerViewScrollListener)

        swipeRefresh.setOnRefreshListener {
            endlessRecyclerViewScrollListener.resetState()
            viewModel.getMenus(1)
        }
    }

    private fun initObservables() {
        viewModel.menus.observe(this, Observer { menusWithPage ->
            adapter.addAll(menusWithPage?.get(menusWithPage.keys.first())
                    , menusWithPage!!.keys.first())
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
