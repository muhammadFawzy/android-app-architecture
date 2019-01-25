package com.example.app.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.app.domain.entity.Menu


@Database(entities = [Menu::class], version = 1, exportSchema = false)
abstract class MenuDB : RoomDatabase() {


    abstract fun daoAccess(): MenuDao


    companion object {
        private var INSTANCE: MenuDB? = null
        private const val DATA_BASE_NAME = "menus"
        fun getInMemoryDatabase(context: Context): MenuDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext
                        , MenuDB::class.java
                        , DATA_BASE_NAME)
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE as MenuDB
        }
    }


}