package com.ecs198f.foodtrucks.models

data class FoodReview(
    val id: String,
    val truckId: String,
    val authorName: String,
    val authorAvatarUrl: String,
    val content: String,
    val imageUrls: List<String>
)
