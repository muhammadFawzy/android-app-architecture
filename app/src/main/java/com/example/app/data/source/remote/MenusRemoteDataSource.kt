package com.example.app.data.source.remote

import com.example.app.data.MenusDataSource

class MenusRemoteDataSource(private val api: MenusApi = RetrofitClient.client.create(MenusApi::class.java)) : MenusDataSource {

    override fun getAllMenus(pageNo: Int) = api.getItems(pageNo)
}