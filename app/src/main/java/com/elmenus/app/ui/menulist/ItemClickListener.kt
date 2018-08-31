package com.elmenus.app.ui.menulist

import android.widget.ImageView
import com.elmenus.app.model.Menu


interface ItemClickListener {
    fun onItemClick(menu: Menu ,shared:ImageView)
}