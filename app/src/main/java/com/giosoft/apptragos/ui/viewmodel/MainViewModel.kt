package com.giosoft.apptragos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.giosoft.apptragos.domain.Repo
import com.giosoft.apptragos.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repo: Repo):ViewModel(){

    val fetchDrinkList = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repo.getDrinkList())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}