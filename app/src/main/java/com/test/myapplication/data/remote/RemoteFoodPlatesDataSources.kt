package com.test.myapplication.data.remote

import com.test.myapplication.data.model.MealList
import com.test.myapplication.repository.WebService

class RemoteFoodPlatesDataSources(private val webService: WebService) {

    suspend fun getResultsFoodPlates(foodName: String): MealList {
       return webService.getResultsFoodPlate(foodName)
    }

    suspend fun getDetailForPlates(idFood: Int): MealList {
        return webService.getDetailForPlate(idFood)
    }

    suspend fun getRandomPlates(): MealList {
        return webService.getRandomPlate()
    }

}