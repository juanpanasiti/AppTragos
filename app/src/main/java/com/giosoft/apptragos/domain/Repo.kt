package com.giosoft.apptragos.domain

import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.data.models.DrinkEntity
import com.giosoft.apptragos.vo.Resource

interface Repo {
    suspend fun getDrinkList(drinkName:String):Resource<List<Drink>>
    suspend fun getFavouriteDrinkById(id:String):Resource<DrinkEntity>
    suspend fun getFavouritesDrink(): Resource<List<DrinkEntity>>
    suspend fun insertDrink(drink:DrinkEntity)
//    suspend fun deleteDrink(drink: Drink)
}