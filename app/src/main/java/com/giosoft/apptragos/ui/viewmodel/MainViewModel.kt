package com.giosoft.apptragos.ui.viewmodel

import androidx.lifecycle.*
import com.giosoft.apptragos.domain.Repo
import com.giosoft.apptragos.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repo: Repo):ViewModel(){

    private val drinksData = MutableLiveData<String>()

    init {
        setDrink("margarita")
    }

    fun setDrink(drinkName:String){
        drinksData.value = drinkName
    }
    val fetchDrinkList = drinksData.distinctUntilChanged().switchMap {drinkName ->
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try {
                emit(repo.getDrinkList(drinkName))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }
}