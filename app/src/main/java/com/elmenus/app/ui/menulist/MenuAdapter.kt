package com.elmenus.app.ui.menulist

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elmenus.app.R
import com.elmenus.app.databinding.MenuItemBinding
import com.elmenus.app.model.Menu

class MenuAdapter(var listener: ItemClickListener) : RecyclerView.Adapter<MenuAdapter.Menuholder>() {
    var menus = listOf<Menu>()


    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): Menuholder {
        var root = LayoutInflater.from(viewGroup.context).inflate(R.layout.menu_item
                , viewGroup, false)
        return Menuholder(root)

    }

    override fun getItemCount(): Int {
        return menus!!.size
    }

    override fun onBindViewHolder(holder: Menuholder, position: Int) {
        holder.binding?.model = menus!![position]
    }

    fun addAll(menus: List<Menu>?) {
        this.menus = menus!!
        notifyDataSetChanged()

    }


    inner class Menuholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: MenuItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)

            itemView.setOnClickListener {
                listener.onItemClick(menus!![adapterPosition], binding?.imageView!!)

            }
        }


    }
}