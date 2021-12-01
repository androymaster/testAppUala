package com.test.myapplication.repository

import com.test.myapplication.data.model.food_plates
import com.test.myapplication.data.remote.RemoteFoodPlatesDataSources

class FoodPlateRepositoryImpl(
    private val dataSources: RemoteFoodPlatesDataSources
) : FoodPlateRepository {

    override suspend fun getResultsFoodPlates(): food_plates = dataSources.getResultsFoodPlates()

    override suspend fun getDetailForPlates(): food_plates = dataSources.getDetailForPlates()

    override suspend fun getRandomPlates(): food_plates = dataSources.getRandomPlates()

}