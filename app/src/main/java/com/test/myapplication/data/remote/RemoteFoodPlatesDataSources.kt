package com.test.myapplication.data.remote

import com.test.myapplication.data.model.food_plates
import com.test.myapplication.repository.WebService

class RemoteFoodPlatesDataSources(private val webService: WebService) {

    suspend fun getResultsFoodPlates(): food_plates {
       return webService.getResultsFoodPlate( s = "Chicken")
    }

    suspend fun getDetailForPlates(): food_plates {
        return webService.getDetailForPlate( i = 12)
    }

    suspend fun getRandomPlates(): food_plates {
        return webService.getRandomPlate()
    }

}