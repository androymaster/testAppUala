package com.test.myapplication.ui.foodPlatesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.test.myapplication.R
import com.test.myapplication.databinding.FragmentFoodListBinding

class FoodListFragment : Fragment(R.layout.fragment_food_list) {

    private lateinit var binding: FragmentFoodListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoodListBinding.bind(view)
    }
}