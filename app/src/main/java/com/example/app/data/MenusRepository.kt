package com.example.app.data

import com.example.app.data.source.remote.MenusRemoteDataSource
import com.example.app.domain.entity.MenuList
import retrofit2.Call

private val defaultRemoteDataSource by lazy { MenusRemoteDataSource() }

class MenusRepository(private val remoteDataSource: MenusRemoteDataSource = defaultRemoteDataSource) : MenusDataSource {

    override fun getAllMenus(pageNo: Int): Call<MenuList> = remoteDataSource.getAllMenus(pageNo)
}