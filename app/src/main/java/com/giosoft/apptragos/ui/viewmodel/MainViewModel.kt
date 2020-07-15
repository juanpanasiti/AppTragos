package com.giosoft.apptragos.ui.viewmodel

import androidx.lifecycle.*
import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.data.models.DrinkEntity
import com.giosoft.apptragos.domain.Repo
import com.giosoft.apptragos.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    }//fetchDrinkList

    fun saveDrink(drink:DrinkEntity){
        viewModelScope.launch {
            repo.insertDrink(drink)
        }
    }//saveDrink()

    fun getFavouritesDrinks() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repo.getFavouritesDrink())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun getFavouriteDrinkById(id:String) = liveData(Dispatchers.IO){
        emit(Resource.Loading<DrinkEntity>())
        try {
            emit(repo.getFavouriteDrinkById(id))
        }catch (e: Exception){
            emit(Resource.Failure<DrinkEntity>(e))
        }
    }

//    fun deleteDrink(drink: Drink){
//        viewModelScope.launch {
//            repo.deleteDrink(drink)
//        }
//    }
}