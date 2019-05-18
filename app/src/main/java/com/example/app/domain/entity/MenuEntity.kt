package com.example.app.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MenuList(@SerializedName("items") val items: List<Menu>)

@Entity
data class Menu(@SerializedName("id") val id: Int
                , @PrimaryKey @SerializedName("name") val name: String
                , @SerializedName("photoUrl") val photoUrl: String
                , @SerializedName("description") val description: String) : Serializable

