package com.elmenus.app.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MenuList(@SerializedName("items") val items: List<Menu>) {

}

@Entity
data class Menu(@PrimaryKey @SerializedName("id") val id: Int
                , @SerializedName("name") val name: String
                , @SerializedName("photoUrl") val photoUrl: String
                , @SerializedName("description") val description: String) : Serializable {
}

