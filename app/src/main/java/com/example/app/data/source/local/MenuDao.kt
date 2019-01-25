package com.example.app.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.app.domain.entity.Menu


@Dao
interface MenuDao {
    /**
     * selecting menus from DB by pageNo.
     * @param pageNo where page no is the first char in the name of menu like (1 - name)
     */
    @Query("SELECT * from menu where menu.name LIKE :pageNo")
    fun getAllMenus(pageNo: String): List<Menu>

    /**
     * insert one menu at a time  with considering no conflict with old data
     * if there is conflict update the old data by new one .
     * @param menu
     */

    @Insert(onConflict = REPLACE)
    fun insert(menu: Menu)

    /**
     * insert punch of menus with considering no conflict with old data
     * if there is conflict update the old data by new one .
     * @param listofmenus
     */

    @Insert(onConflict = REPLACE)
    fun insertAll(menus: List<Menu>)

    /**
     * Delete all data
     */

    @Query("DELETE FROM menu")
    fun deleteAllData()
}