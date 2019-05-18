package com.example.app.presentation.feature.menulist

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.domain.entity.Menu
import com.example.app.domain.entity.MenuList
import com.example.app.domain.usecase.GetMenusUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel(private val getMenusUseCase: GetMenusUseCase) : ViewModel(), Callback<MenuList> {


    var loadMore = ObservableInt(View.GONE)
    var networkError = ObservableInt(View.GONE)
    var loading = ObservableBoolean(false)
    var menus = MutableLiveData<Map<Int, List<Menu>>>()
    private var lastPageNo: Int? = 0


    init {
        getMenus(1)
    }

    fun getMenus(pageNo: Int) {
        if (pageNo == 1) loading.set(true) else loadMore.set(View.VISIBLE)
        lastPageNo = pageNo
        getMenusUseCase.getAllMenus(pageNo).enqueue(this)
    }

    override fun onResponse(call: Call<MenuList>?, response: Response<MenuList>) {
        loading.set(false)
        loadMore.set(View.GONE)
        networkError.set(View.GONE)

        if (response.isSuccessful)
            menus.value = mapOf(lastPageNo!! to response.body()!!.items)
    }

    override fun onFailure(call: Call<MenuList>?, t: Throwable?) {
        loading.set(false)
        loadMore.set(View.GONE)
        networkError.set(View.VISIBLE)
    }
}