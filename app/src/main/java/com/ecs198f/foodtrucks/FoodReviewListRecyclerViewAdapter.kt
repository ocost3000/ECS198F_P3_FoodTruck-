package com.ecs198f.foodtrucks

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FoodReviewListRecyclerViewAdapter(private var items: List<>):
    RecyclerView.Adapter<FoodReviewListRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: FoodReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
        // Copy over from FoodItemListRecyclerViewAdapter
    }

}