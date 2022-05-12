package com.ecs198f.foodtrucks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckMenuBinding
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckReviewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckMenuFragment(val name: String, val id: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodTruckMenuBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodItemListRecyclerViewAdapter(listOf())

        binding.apply {
            foodItemListRecyclerView.apply {
                adapter = recyclerViewAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        (requireActivity() as MainActivity).apply {

            foodTruckService.listFoodItems(this@FoodTruckMenuFragment.id).enqueue(object : Callback<List<FoodItem>> {
                override fun onResponse(
                    call: Call<List<FoodItem>>,
                    response: Response<List<FoodItem>>
                ) {
                    recyclerViewAdapter.updateItems(response.body()!!)
                }

                override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                    throw t
                }
            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}