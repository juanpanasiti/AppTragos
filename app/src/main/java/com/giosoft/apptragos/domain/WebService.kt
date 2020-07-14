package com.giosoft.apptragos.domain

import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.data.models.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getDrinkByName(@Query("s") drinkName: String): DrinkList
}