package com.example.app.presentation.feature.menulist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.app.R
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
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(MenuViewModel::class.java)
        binding.viewmodel = viewModel

        adapter = MenuAdapter(this)
        recycler_menus.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, VERTICAL, false)
        recycler_menus.adapter = adapter
    }

    private fun initListeners() {
        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(recycler_menus.layoutManager as androidx.recyclerview.widget.LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: androidx.recyclerview.widget.RecyclerView) {
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
