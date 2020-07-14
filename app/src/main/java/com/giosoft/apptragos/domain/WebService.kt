package com.giosoft.apptragos.domain

import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.data.models.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php?s=")
    suspend fun getDrinkByName(@Query("drinkName") drinkName: String): DrinkList
}