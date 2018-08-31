package mvvm.f4wzy.com.samplelogin.network

import com.elmenus.app.model.MenuList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BackEndApi {

    @Headers("Content-Type: application/json")
    @GET("/items/{page}")
    fun getItems(@Path("page") pageNumber: Int): Call<MenuList>

}

