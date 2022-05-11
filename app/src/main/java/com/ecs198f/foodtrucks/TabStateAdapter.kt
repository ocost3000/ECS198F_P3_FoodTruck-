package com.ecs198f.foodtrucks

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabStateAdapter(fragment: Fragment, val name: String, val id: String) : FragmentStateAdapter(fragment) {
    // This adapter is responsible for determining which fragment
    // the ViewPager2 should display given an index of a tab.

    // 2 for the two subfragments?
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        TODO("Need to have logic to choose between the two subfragments")
        if (position == 0) {
            val fragment = FoodTruckMenuFragment(name, id)
        } else {
            val fragment = FoodTruckReviewsFragment(name, id)
        }
        fragment.arguments =
    }
}