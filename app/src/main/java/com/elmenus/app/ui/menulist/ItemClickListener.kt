package com.elmenus.app.ui.menulist

import android.widget.ImageView
import com.elmenus.app.model.Menu


interface ItemClickListener {
    /**
     * interface using when item in the menu clicked
     * @param menu as clicked item
     * @param shared as shared element between menu list and details
     */
    fun onItemClick(menu: Menu, shared: ImageView)
}