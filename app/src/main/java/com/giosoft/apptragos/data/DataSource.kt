package com.giosoft.apptragos.data

import android.util.Log
import com.giosoft.apptragos.AppDatabase
import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.data.models.DrinkEntity
import com.giosoft.apptragos.vo.Resource
import com.giosoft.apptragos.vo.RetrofitClient

class DataSource(private val appDatabase: AppDatabase) {

    suspend fun getDrinkByName(drinkName:String):Resource<List<Drink>>{
        val list = Resource.Success(RetrofitClient.webService.getDrinkByName(drinkName).drinkList)
        Log.d("QUERY", "$list")
        return list
    }

    suspend fun insertDrinkIntoRoom(drink:DrinkEntity){
        appDatabase.drinkDAO().insertFavorite(drink)
    }

    suspend fun getFavouritesDrink(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.drinkDAO().getAllFavoritesDrinks())
    }

//    suspend fun deleteDrink(drink: Drink){
//        appDatabase.drinkDAO().deleteDrink(drink)
//    }

    suspend fun getFavouriteDrinkById(id: String): Resource<DrinkEntity> {
        return Resource.Success(appDatabase.drinkDAO().getFavouriteDrinkById(id))
    }

}