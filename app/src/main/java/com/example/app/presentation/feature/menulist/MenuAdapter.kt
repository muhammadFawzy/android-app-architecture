package com.example.app.presentation.feature.menulist

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.R
import com.example.app.databinding.MenuItemBinding
import com.example.app.domain.entity.Menu

class MenuAdapter(var listener: ItemClickListener) : RecyclerView.Adapter<MenuAdapter.Menuholder>() {
    var menus = arrayListOf<Menu>()


    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): Menuholder {
        val root = LayoutInflater.from(viewGroup.context).inflate(R.layout.menu_item
                , viewGroup, false)
        return Menuholder(root)

    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: Menuholder, position: Int) {
        holder.binding?.model = menus[position]
    }

    /**
     * add menus to the recycler view with considering if it's page 1
     * then delete all previous data before adding
     * @param lisOfMenus
     * @param currentPage
     */
    fun addAll(menus: List<Menu>?, currentPage: Int) {
        if (currentPage == 1) this.menus.clear()
        this.menus.addAll(menus!!)
        notifyDataSetChanged()

    }


    inner class Menuholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: MenuItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)

            itemView.setOnClickListener {
                listener.onItemClick(menus[adapterPosition], binding?.imageView!!)

            }
        }


    }
}