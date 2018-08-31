package com.elmenus.app.ui.menulist

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.view.View

class MenuViewModel : ViewModel() {

    var loadMore = ObservableInt(View.GONE)
    var loading = ObservableBoolean(false)

}