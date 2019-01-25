package com.example.app.data

import com.example.app.data.source.remote.MenusRemoteDataSource

class MenusRepository : MenusDataSource {
    private val remoteDataSource by lazy { MenusRemoteDataSource() }

    override fun getAllMenus(pageNo: Int) = remoteDataSource.getAllMenus(pageNo)
}