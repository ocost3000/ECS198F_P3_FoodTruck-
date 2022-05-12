package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckDetailFragment : Fragment() {
    private val args: FoodTruckDetailFragmentArgs by navArgs()
    private val tabNames = listOf<String>("Menu", "Reviews")
    private lateinit var tabStateAdapter: TabStateAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var name: String
    private lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodTruckDetailBinding.inflate(inflater, container, false)

        args.foodTruck.let {
            binding.apply {
                Glide.with(root).load(it.imageUrl).into(foodTruckDetailImage)
                foodTruckDetailPriceLevel.text = "$".repeat(it.priceLevel)
                foodTruckDetailLocation.text = it.location
                foodTruckDetailTime.text = it.formattedTimeInterval
            }
            // passing title through safeArgs instead of through api response
            (requireActivity() as MainActivity).title = it.name
            name = it.name
            id = it.id
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // send argument to subfragment
        tabStateAdapter = TabStateAdapter(this, name, id)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = tabStateAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}