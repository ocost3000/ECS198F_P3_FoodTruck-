package com.ecs198f.foodtrucks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckMenuFragment(val name: String, val id: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerViewAdapter = FoodItemListRecyclerViewAdapter(listOf())

        (requireActivity() as MainActivity).apply {
            title = this@FoodTruckMenuFragment.name

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

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}