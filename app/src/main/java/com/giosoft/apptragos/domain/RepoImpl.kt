package com.giosoft.apptragos.domain

import com.giosoft.apptragos.data.DataSource
import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override fun getDrinkList(): Resource<List<Drink>> {
        return dataSource.generateDrinkList
    }
}