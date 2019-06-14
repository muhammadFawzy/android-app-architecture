package com.example.app.presentation.feature.menulist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.app.R
import com.example.app.domain.entity.Menu
import kotlinx.android.synthetic.main.menu_item.*

class MenuAdapter(var listener: ItemClickListener) : androidx.recyclerview.widget.RecyclerView.Adapter<MenuHolder>() {
    var menus = arrayListOf<Menu>()


    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): MenuHolder {
        val root = LayoutInflater.from(viewGroup.context).inflate(R.layout.menu_item
                , viewGroup, false)
        return MenuHolder(root)

    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val menu = menus[position]
        holder.bind(menu)
        holder.containerView.setOnClickListener { listener.onItemClick(menu, holder.ivImg) }
    }

    /**
     * add menus to the recycler view with considering if it's page 1
     * then delete all previous data before adding
     * @param lisOfMenus
     * @param currentPage
     */
    fun addAll(menus: List<Menu>, currentPage: Int) {
        if (currentPage == 1) this.menus.clear()
        this.menus.addAll(menus)
        notifyDataSetChanged()

    }
}