package com.giosoft.apptragos.domain

import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.vo.Resource

interface Repo {
    suspend fun getDrinkList(drinkName:String):Resource<List<Drink>>
}