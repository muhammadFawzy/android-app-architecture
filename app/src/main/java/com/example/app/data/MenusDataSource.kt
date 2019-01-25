package com.example.app.data

import com.example.app.domain.entity.MenuList
import retrofit2.Call

interface MenusDataSource {
    fun getAllMenus(pageNo: Int): Call<MenuList>

    fun getMenuDetails(id: Int)
}