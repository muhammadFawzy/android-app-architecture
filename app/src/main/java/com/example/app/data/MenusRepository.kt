package com.example.app.data

import com.example.app.data.source.local.MenusLocalDataSource
import com.example.app.data.source.remote.MenusRemoteDataSource

class MenusRepository : MenusDataSource {
    private val localDataSource by lazy { MenusLocalDataSource() }
    private val remoteDataSource by lazy { MenusRemoteDataSource() }

    override fun getAllMenus(pageNo: Int) = remoteDataSource.getAllMenus(pageNo)
}