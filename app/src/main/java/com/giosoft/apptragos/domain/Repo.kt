package com.giosoft.apptragos.domain

import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.vo.Resource

interface Repo {
    fun getDrinkList():Resource<List<Drink>>
}