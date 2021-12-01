package com.test.myapplication.repository

import com.google.gson.GsonBuilder
import com.test.myapplication.application.AppConstants
import com.test.myapplication.data.model.MealList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("api/json/v1/1/search.php")
    suspend fun getResultsFoodPlate(
        @Query("s") foodName: String
    ): MealList

    @GET("api/json/v1/1/lookup.php")
    suspend fun getDetailForPlate(
        @Query("i") id: Int
    ): MealList

    @GET("api/json/v1/1/random.php")
    suspend fun getRandomPlate(): MealList

}

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}