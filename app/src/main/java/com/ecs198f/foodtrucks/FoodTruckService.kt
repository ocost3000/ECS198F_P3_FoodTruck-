package com.ecs198f.foodtrucks

import com.ecs198f.foodtrucks.models.FoodItem
import com.ecs198f.foodtrucks.models.FoodReview
import com.ecs198f.foodtrucks.models.FoodTruck
import retrofit2.Call
import retrofit2.http.*

interface FoodTruckService {
    @GET("food-trucks")
    fun listFoodTrucks(): Call<List<FoodTruck>>

    @GET("food-trucks/{truckId}/items")
    fun listFoodItems(@Path("truckId") truckId: String): Call<List<FoodItem>>

    @GET("/food-trucks/{truckId}/reviews")
    fun listFoodReviews(@Path("truckId") truckId: String): Call<List<FoodReview>>

    @POST("/food-trucks/{truckId}/reviews")
    fun postFoodReviews(@Path("truckId") truckId: String,
                        @Body request: PostReviewRequest,
                        @Header("Authorization") auth: String): Call<Unit>

}