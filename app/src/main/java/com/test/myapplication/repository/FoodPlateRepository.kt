package com.test.myapplication.repository

import com.test.myapplication.data.model.food_plates

interface FoodPlateRepository {

    suspend fun getResultsFoodPlates(): food_plates
    suspend fun getDetailForPlates(): food_plates
    suspend fun getRandomPlates(): food_plates
}