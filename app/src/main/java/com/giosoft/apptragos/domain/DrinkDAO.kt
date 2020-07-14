package com.giosoft.apptragos.domain

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giosoft.apptragos.data.models.DrinkEntity

interface DrinkDAO {

    @Query("SELECT * FROM drinks")
    suspend fun getAllFavoritesDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink:DrinkEntity)
}