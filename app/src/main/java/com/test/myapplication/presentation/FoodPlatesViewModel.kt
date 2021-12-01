package com.test.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.test.myapplication.core.Resources
import com.test.myapplication.repository.FoodPlateRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class FoodPlatesViewModel(private val repo: FoodPlateRepository) : ViewModel() {

    fun getResultsPlates() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resources.Loading())
        try {
            emit(Resources.Success(repo.getResultsFoodPlates()))
        }catch (e: Exception){
            emit(Resources.Failure(e))
        }
    }

    fun getDetailPlates() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resources.Loading())
        try {
            emit(Resources.Success(repo.getDetailForPlates()))
        }catch (e: Exception){
            emit(Resources.Failure(e))
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