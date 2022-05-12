package com.ecs198f.foodtrucks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckReviewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckReviewsFragment(val name: String, val id: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodTruckReviewsBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodReviewListRecyclerViewAdapter(listOf())

        binding.apply {
            foodReviewListRecyclerView.apply {
                adapter = recyclerViewAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        (requireActivity() as MainActivity).apply {

            foodTruckService.listFoodReviews(this@FoodTruckReviewsFragment.id).enqueue(object :
                Callback<List<FoodReview>> {
                override fun onResponse(
                    call: Call<List<FoodReview>>,
                    response: Response<List<FoodReview>>
                ) {
                    recyclerViewAdapter.updateReviews(response.body()!!)
                }

                override fun onFailure(call: Call<List<FoodReview>>, t: Throwable) {
                    throw t
                }
            })
        }

        return binding.root

        TODO("The visibility attribute can be used to show/hide Views in the Fragment")
        // use this to toggle between SIGN IN and POST
    }
}