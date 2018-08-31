package com.elmenus.app.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.elmenus.app.model.Menu


@Dao
interface MenuDao {
    @Query("SELECT * from menu")
    fun getAllMenus(): List<Menu>

    @Insert(onConflict = REPLACE)
    fun insert(menu: Menu)

    @Insert(onConflict = REPLACE)
    fun insertAll(menus: List<Menu>)
}