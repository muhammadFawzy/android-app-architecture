package com.example.app.data.source.local

import com.example.app.data.MenusDataSource
import com.example.app.domain.entity.MenuList
import retrofit2.Call

class MenusLocalDataSource : MenusDataSource {
    override fun getAllMenus(pageNo: Int): Call<MenuList> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}