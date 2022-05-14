package com.ecs198f.foodtrucks.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ecs198f.foodtrucks.fragments.FoodTruckMenuFragment
import com.ecs198f.foodtrucks.fragments.FoodTruckReviewsFragment

class TabStateAdapter(fragment: Fragment, val name: String, val id: String) : FragmentStateAdapter(fragment) {
    // This adapter is responsible for determining which fragment
    // the ViewPager2 should display given an index of a tab.

    // 2 for the two subfragments?
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment = if (position == 0) {
            FoodTruckMenuFragment(name, id)
        } else {
            FoodTruckReviewsFragment(name, id)
        }
        return fragment
    }
}