package com.example.app.presentation.feature.menulist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.domain.entity.Menu
import com.example.app.presentation.feature.menudetails.MenuDetailsActivity
import com.example.app.utils.EndlessRecyclerViewScrollListener


class MenusListActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var adapter: MenuAdapter
    lateinit var viewModel: MenuViewModel
    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus_list)
        viewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        setupRecycler()
        initObservables()
        initListeners()
    }

    private fun setupRecycler() {
        adapter = MenuAdapter(this)
        recycler_menus.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_menus.adapter = adapter
    }

    private fun initListeners() {
        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(recycler_menus.layoutManager as androidx.recyclerview.widget.LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getMenus(page)

            }
        }

        recycler_menus.addOnScrollListener(endlessRecyclerViewScrollListener)

        swipeRefresh.setOnRefreshListener {
            endlessRecyclerViewScrollListener.resetState()
            viewModel.getMenus()
        }
    }

    private fun initObservables() {
        viewModel.loadMore.observe(this, Observer { visibility ->
            progresssLoadMore.visibility = visibility
        })
        viewModel.networkError.observe(this, Observer { visibility ->
            tvNetworkError.visibility = visibility
        })
        viewModel.loading.observe(this, Observer { isLoading ->
            swipeRefresh.isRefreshing = isLoading
        })

        viewModel.menus.observe(this, Observer { menusWithPage ->
            menusWithPage[menusWithPage.keys.first()].let {
                adapter.addAll(it, menusWithPage.keys.first())
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
