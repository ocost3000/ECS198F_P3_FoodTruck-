package com.ecs198f.foodtrucks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecs198f.foodtrucks.databinding.FoodReviewBinding

class FoodReviewListRecyclerViewAdapter(private var reviews: List<FoodReview>):
    RecyclerView.Adapter<FoodReviewListRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: FoodReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Copy over from FoodItemListRecyclerViewAdapter
        val binding = FoodReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        reviews[position].let {
            holder.binding.apply {
                authorName.text = it.authorName
                content.text = it.content
                Glide.with(root).load(it.authorAvatarUrl).into(authorAvatar)
            }
        }
    }

    override fun getItemCount(): Int = reviews.size

    fun updateReviews(reviews: List<FoodReview>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }

}