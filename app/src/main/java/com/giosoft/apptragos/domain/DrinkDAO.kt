package com.giosoft.apptragos.domain

import androidx.room.*
import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.data.models.DrinkEntity

@Dao
interface DrinkDAO {

    @Query("SELECT * FROM drinks")
    suspend fun getAllFavoritesDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink:DrinkEntity)

    @Delete()
    suspend fun deleteDrink(drink:DrinkEntity)

    @Query("SELECT * FROM drinks WHERE idDrink = :id")
    suspend fun getFavouriteDrinkById(id:String):DrinkEntity
}