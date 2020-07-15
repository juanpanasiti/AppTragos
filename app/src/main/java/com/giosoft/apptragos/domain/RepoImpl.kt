package com.giosoft.apptragos.domain

import com.giosoft.apptragos.data.DataSource
import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.data.models.DrinkEntity
import com.giosoft.apptragos.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override suspend fun getDrinkList(drinkName:String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getFavouriteDrinkById(id: String): Resource<DrinkEntity> {
        return dataSource.getFavouriteDrinkById(id)
    }

    override suspend fun getFavouritesDrink(): Resource<List<DrinkEntity>> {
        return dataSource.getFavouritesDrink()
    }

    override suspend fun insertDrink(drink: DrinkEntity) {
        dataSource.insertDrinkIntoRoom(drink)
    }

    override suspend fun deleteDrink(drink:DrinkEntity) {
        dataSource.deleteDrink(drink)
    }
}