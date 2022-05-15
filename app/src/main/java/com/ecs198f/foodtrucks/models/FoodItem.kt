package com.ecs198f.foodtrucks.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodItem(
    val id: String,
    val truckId: String,
    val name: String,
    val description: String,
    val price: Double,
    val taxIncluded: Boolean
) {
    @PrimaryKey(autoGenerate = true) var primaryKey: Int = 0
}
