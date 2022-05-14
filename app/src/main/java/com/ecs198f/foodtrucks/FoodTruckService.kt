package com.ecs198f.foodtrucks

import com.ecs198f.foodtrucks.models.FoodItem
import com.ecs198f.foodtrucks.models.FoodReview
import com.ecs198f.foodtrucks.models.FoodTruck
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodTruckService {
    @GET("food-trucks")
    fun listFoodTrucks(): Call<List<FoodTruck>>

    @GET("food-trucks/{id}/items")
    fun listFoodItems(@Path("id") truckId: String): Call<List<FoodItem>>

    @GET("/food-trucks/{id}/reviews")
    fun listFoodReviews(@Path("id") truckId: String): Call<List<FoodReview>>
}