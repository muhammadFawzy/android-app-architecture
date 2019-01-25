package com.example.app.data.source.remote

import com.example.app.domain.entity.MenuList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MenusApi {

    @Headers("Content-Type: application/json")
    @GET("/items/{page}")
    fun getItems(@Path("page") pageNumber: Int): Call<MenuList>

}

