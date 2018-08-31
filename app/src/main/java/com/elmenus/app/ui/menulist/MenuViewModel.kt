package com.elmenus.app.ui.menulist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import com.elmenus.app.db.MenuDB
import com.elmenus.app.model.Menu
import com.elmenus.app.model.MenuList
import mvvm.f4wzy.com.samplelogin.network.BackEndApi
import mvvm.f4wzy.com.samplelogin.network.WebServiceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel : ViewModel(), Callback<MenuList> {

    lateinit var menuDB: MenuDB

    var loadMore = ObservableInt(View.GONE)
    var networkError = ObservableInt(View.GONE)
    var loading = ObservableBoolean(false)
    var menus = MutableLiveData<List<Menu>>()
    var lastPageNo: Int? = 0


    fun initData(menuDB: MenuDB) {
        this.menuDB = menuDB
        getMenus(1)
    }

    fun getMenus(pageNo: Int) {
        if (pageNo == 1) loading.set(true) else loadMore.set(View.VISIBLE)
        lastPageNo = pageNo
        WebServiceClient.client.create(BackEndApi::class.java)
                .getItems(pageNo).enqueue(this)


    }

    override fun onResponse(call: Call<MenuList>?, response: Response<MenuList>?) {
        loading.set(false)
        loadMore.set(View.GONE)
        networkError.set(View.GONE)
        if (response?.isSuccessful!!) {
            menuDB.daoAccess().insertAll(response.body().items)
            menus.value = response.body().items
        }
    }

    override fun onFailure(call: Call<MenuList>?, t: Throwable?) {
        loading.set(false)
        loadMore.set(View.GONE)
        //search by page no
        var menusStored = menuDB.daoAccess().getAllMenus("$lastPageNo - %")
        menus.value = menusStored
        if (menusStored.isEmpty() && lastPageNo == 1) networkError.set(View.VISIBLE) else networkError.set(View.GONE)


    }


}