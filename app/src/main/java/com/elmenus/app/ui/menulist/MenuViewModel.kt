package com.elmenus.app.ui.menulist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.view.View
import com.elmenus.app.model.Menu
import com.elmenus.app.model.MenuList
import mvvm.f4wzy.com.samplelogin.network.BackEndApi
import mvvm.f4wzy.com.samplelogin.network.WebServiceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel : ViewModel(), Callback<MenuList> {


    var loadMore = ObservableInt(View.GONE)
    var loading = ObservableBoolean(false)
    var menus = MutableLiveData<List<Menu>>()


    fun getMenus(pageNo: Int) {
        if (pageNo == 1) loading.set(true) else loadMore.set(View.VISIBLE)
        WebServiceClient.client.create(BackEndApi::class.java)
                .getItems(pageNo).enqueue(this)


    }

    override fun onResponse(call: Call<MenuList>?, response: Response<MenuList>?) {
        loading.set(false)
        loadMore.set(View.GONE)
        if (response?.isSuccessful!!) {
            menus.value = response.body().items
        }
    }

    override fun onFailure(call: Call<MenuList>?, t: Throwable?) {
        loading.set(false)
        loadMore.set(View.GONE)
    }

}