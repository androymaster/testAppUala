package com.test.myapplication.repository

import com.test.myapplication.data.model.MealList

interface FoodPlateRepository {

    suspend fun getResultsFoodPlates(foodName: String): MealList
    suspend fun getDetailForPlates(idFood: Int): MealList
    suspend fun getRandomPlates(): MealList
}