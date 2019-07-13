package com.example.app.presentation.feature.menulist

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.domain.entity.Menu
import com.example.app.domain.entity.MenuList
import com.example.app.domain.usecase.GetMenusUseCase
import com.example.app.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel(private val getMenusUseCase: GetMenusUseCase = GetMenusUseCase()) : ViewModel(), Callback<MenuList> {


    var loadMore = SingleLiveEvent<Int>()
    var networkError = SingleLiveEvent<Int>()
    var loading = SingleLiveEvent<Boolean>()
    var menus = MutableLiveData<Map<Int, List<Menu>>>()

    private var lastPageNo: Int = 0

    init {
        getMenus()
    }

    fun getMenus(pageNo: Int = 1) {
        if (pageNo == 1) loading.value = true else loadMore.value = View.VISIBLE
        lastPageNo = pageNo
        getMenusUseCase.getAllMenus(pageNo).enqueue(this)
    }

    override fun onResponse(call: Call<MenuList>?, response: Response<MenuList>) {
        loading.value = false
        loadMore.value = View.GONE
        networkError.value = View.GONE

        if (response.isSuccessful)
            menus.value = mapOf(lastPageNo to response.body()!!.items)
    }

    override fun onFailure(call: Call<MenuList>?, t: Throwable?) {
        loading.value = false
        loadMore.value = View.GONE
        networkError.value = View.VISIBLE
    }
}