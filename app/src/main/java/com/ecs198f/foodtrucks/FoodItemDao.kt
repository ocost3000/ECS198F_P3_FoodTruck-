package com.ecs198f.foodtrucks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ecs198f.foodtrucks.models.FoodItem

@Dao
interface FoodItemDao {
    @Query("SELECT * FROM FoodItem")
    suspend fun listAllFoodItems(): List<FoodItem>

    @Query("SELECT * FROM FoodItem WHERE truckId = :truckId")
    suspend fun listFoodTruckFoodItems(truckId: String): List<FoodItem>

    @Insert
    suspend fun addFoodItem(foodItem: FoodItem)

    @Insert
    suspend fun addFoodItems(foodItems: List<FoodItem>)

    @Delete
    suspend fun removeFoodItem(foodItem: FoodItem)

    @Query("Delete FROM FoodItem")
    suspend fun removeAllFoodItem()
}