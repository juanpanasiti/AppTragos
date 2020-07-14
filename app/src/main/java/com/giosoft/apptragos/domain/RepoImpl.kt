package com.giosoft.apptragos.domain

import com.giosoft.apptragos.data.DataSource
import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override suspend fun getDrinkList(drinkName:String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }
}