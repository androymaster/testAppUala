package com.test.myapplication.ui.foodPlatesDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.test.myapplication.R
import com.test.myapplication.databinding.FragmentFoodPlateDetailBinding

class FoodPlateDetailFragment : Fragment(R.layout.fragment_food_plate_detail) {

    private lateinit var binding: FragmentFoodPlateDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoodPlateDetailBinding.bind(view)
    }

}