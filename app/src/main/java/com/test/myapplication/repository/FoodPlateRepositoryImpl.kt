package com.test.myapplication.repository

import com.test.myapplication.data.model.MealList
import com.test.myapplication.data.remote.RemoteFoodPlatesDataSources

class FoodPlateRepositoryImpl(
    private val dataSources: RemoteFoodPlatesDataSources
) : FoodPlateRepository {

    override suspend fun getResultsFoodPlates(foodName: String): MealList = dataSources.getResultsFoodPlates(foodName)

    override suspend fun getDetailForPlates(idFood: Int): MealList = dataSources.getDetailForPlates(idFood)

    override suspend fun getRandomPlates(): MealList = dataSources.getRandomPlates()

}