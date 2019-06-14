package com.example.app.domain.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MenuList(@SerializedName("items") val items: List<Menu>)

data class Menu(@SerializedName("id") val id: Int
                , @SerializedName("name") val name: String
                , @SerializedName("photoUrl") val photoUrl: String
                , @SerializedName("description") val description: String) : Serializable

