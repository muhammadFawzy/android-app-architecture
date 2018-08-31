package com.elmenus.app.ui.menulist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elmenus.app.R

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.Menuholder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): Menuholder {
        var root = LayoutInflater.from(viewGroup.context).inflate(R.layout.menu_item
                , viewGroup, false)
        return Menuholder(root)

    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: Menuholder, position: Int) {
    }


    class Menuholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}