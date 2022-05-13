package com.ecs198f.foodtrucks

data class PostReviewRequest(
    val content: String,
    val imageUrls: List<String>
)