package com.giosoft.apptragos.data

import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.vo.Resource
import com.giosoft.apptragos.vo.RetrofitClient

class DataSource {

    suspend fun getDrinkByName(drinkName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webService.getDrinkByName(drinkName).drinkList)
    }


}