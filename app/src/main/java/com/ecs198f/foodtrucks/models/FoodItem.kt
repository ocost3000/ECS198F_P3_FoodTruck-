package com.ecs198f.foodtrucks.models

data class FoodItem(
    val id: String,
    val truckId: String,
    val name: String,
    val description: String,
    val price: Double,
    val taxIncluded: Boolean
)
