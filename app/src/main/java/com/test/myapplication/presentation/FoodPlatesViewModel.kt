package com.test.myapplication.presentation

import androidx.lifecycle.*
import com.test.myapplication.core.Resources
import com.test.myapplication.repository.FoodPlateRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class FoodPlatesViewModel(private val repo: FoodPlateRepository) : ViewModel() {

    private val foodData = MutableLiveData<String>()
    private val idFoods = MutableLiveData<Int>()

    fun setFood(foodName:String){
        foodData.value = foodName
    }

    fun setIdFood(idFood:Int){
        idFoods.value = idFood
    }

    init{
        setFood("Apple")
        setIdFood(1000)
    }

    fun getResultsPlates() = foodData.distinctUntilChanged().switchMap { nameFood ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resources.Loading())
            try {
                emit(Resources.Success(repo.getResultsFoodPlates(nameFood)))
            } catch (e: Exception) {
                emit(Resources.Failure(e))
            }
        }
    }

    fun getDetailPlates() = idFoods.distinctUntilChanged().switchMap { foodID ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resources.Loading())
            try {
                emit(Resources.Success(repo.getDetailForPlates(foodID)))
            } catch (e: Exception) {
                emit(Resources.Failure(e))
            }
        }
    }

    fun getRandomPlates() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resources.Loading())
        try {
            emit(Resources.Success(repo.getRandomPlates()))
        }catch (e: Exception){
            emit(Resources.Failure(e))
        }
    }
}

class FoodViewModelFactory(private val repo: FoodPlateRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FoodPlateRepository::class.java).newInstance(repo)
    }
}