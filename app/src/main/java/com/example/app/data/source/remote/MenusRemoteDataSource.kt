package com.example.app.data.source.remote

import com.example.app.data.MenusDataSource
import com.example.app.network.MenusApi
import com.example.app.network.RetrofitClient

class MenusRemoteDataSource(private val api: MenusApi = RetrofitClient.client.create(MenusApi::class.java)) : MenusDataSource {

    override fun getAllMenus(pageNo: Int) = api.getItems(pageNo)

    override fun getMenuDetails(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}