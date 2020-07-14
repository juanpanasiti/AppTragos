package com.giosoft.apptragos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.giosoft.apptragos.data.models.DrinkEntity
import com.giosoft.apptragos.domain.DrinkDAO

@Database(entities = arrayOf(DrinkEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun drinkDAO(): DrinkDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "drinks_table").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}