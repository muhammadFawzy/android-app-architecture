package com.example.app.presentation.feature.menulist.mocking

import com.example.app.data.source.remote.MenusApi
import com.example.app.domain.entity.Menu
import com.example.app.domain.entity.MenuList
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitStubbing(val success: Boolean = true, val enqueue: Boolean = true) : MenusApi {
    override fun getItems(pageNumber: Int): Call<MenuList> {
        return object : Call<MenuList> {
            override fun request(): Request = Request.Builder().build()

            override fun enqueue(callback: Callback<MenuList>) {
                if (enqueue) {
                    if (success)
                        callback.onResponse(this, Response.success(MenuList(listOf(Menu(1, "menu", "m", "")))))
                    else
                        callback.onFailure(this, Throwable("error"))
                }
            }

            override fun isExecuted(): Boolean = false

            override fun clone(): Call<MenuList> = this

            override fun isCanceled(): Boolean = false

            override fun cancel() {}

            override fun execute(): Response<MenuList> =
                    if (success) {
                        Response.success(MenuList(listOf(Menu(1, "menu", "m", ""))))
                    } else
                        Response.error(500, ResponseBody.create(MediaType.get("application/json"), "error"))
        }
    }
}