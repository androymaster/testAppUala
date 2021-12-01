package com.test.myapplication.ui.foodPlatesDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import com.test.myapplication.R
import com.test.myapplication.core.Resources
import com.test.myapplication.data.remote.RemoteFoodPlatesDataSources
import com.test.myapplication.databinding.FragmentFoodPlateDetailBinding
import com.test.myapplication.presentation.FoodPlatesViewModel
import com.test.myapplication.presentation.FoodViewModelFactory
import com.test.myapplication.repository.FoodPlateRepositoryImpl
import com.test.myapplication.repository.RetrofitClient

class FoodPlateDetailFragment : Fragment(R.layout.fragment_food_plate_detail) {

    private lateinit var binding: FragmentFoodPlateDetailBinding
    private val args by navArgs<FoodPlateDetailFragmentArgs>()

    private val viewModel by viewModels<FoodPlatesViewModel> {
        FoodViewModelFactory(
            FoodPlateRepositoryImpl(
                RemoteFoodPlatesDataSources(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoodPlateDetailBinding.bind(view)

        val id = args.idFood
        viewModel.setIdFood(id)

        viewModel.getDetailPlates().observe(viewLifecycleOwner, { result ->
            when(result){
                is Resources.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resources.Success ->{
                    binding.progressBar.visibility = View.GONE
                    if (result.data.mealList.isEmpty()){
                        return@observe
                    }else {
                        binding.progressBar.visibility = View.GONE
                        binding.imgBackground.load(result.data.mealList[0].strMealThumb)
                        binding.txtTitlePlate.text = result.data.mealList[0].strMeal
                        binding.description.text = result.data.mealList[0].strInstructions
                        binding.listIngredients.text = result.data.mealList[0].strIngredient1
                    }
                }
                is Resources.Failure ->{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }
}