package com.elmenus.app.ui.menulist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
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

    private lateinit var menuDB: MenuDB

    var loadMore = ObservableInt(View.GONE)
    var networkError = ObservableInt(View.GONE)
    var loading = ObservableBoolean(false)
    var menus = MutableLiveData<Map<Int, List<Menu>>>()
    private var lastPageNo: Int? = 0

    /**
     * initialize view model for the first time
     * @param menuDB fot retrieve data from DB
     * and call getMenu for loading menu with page no when activity created.
     */
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
            menus.value = mapOf(lastPageNo!! to response.body().items)
        }
    }

    override fun onFailure(call: Call<MenuList>?, t: Throwable?) {
        loading.set(false)
        loadMore.set(View.GONE)

        val menusStored = menuDB.daoAccess().getAllMenus("$lastPageNo - %")
        menus.value = mapOf(lastPageNo!! to menusStored)

        // if it's page 1 and empty then there are network error
        if (menusStored.isEmpty() && lastPageNo == 1) networkError.set(View.VISIBLE)
        else networkError.set(View.GONE)


    }


}