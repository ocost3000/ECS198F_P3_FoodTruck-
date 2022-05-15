package com.ecs198f.foodtrucks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ecs198f.foodtrucks.models.FoodTruck

@Dao
interface FoodTruckDao {
    @Query("SELECT * FROM FoodTruck")
    suspend fun listAllFoodTrucks(): List<FoodTruck>

    @Query("SELECT * FROM FoodTruck WHERE id = :id")
    suspend fun getFoodTruck(id: String): FoodTruck

    @Insert
    suspend fun addFoodTruck(foodTruck: FoodTruck)

    @Insert
    suspend fun addFoodTrucks(foodTrucks: List<FoodTruck>)

    @Delete
    suspend fun removeFoodTruck(foodTruck: FoodTruck)

    @Query("DELETE FROM FoodTruck")
    suspend fun removeAllFoodTruck()
}