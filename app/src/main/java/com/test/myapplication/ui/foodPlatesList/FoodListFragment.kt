package com.test.myapplication.ui.foodPlatesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.myapplication.R
import com.test.myapplication.core.Resources
import com.test.myapplication.data.model.food_plates
import com.test.myapplication.data.remote.RemoteFoodPlatesDataSources
import com.test.myapplication.databinding.FragmentFoodListBinding
import com.test.myapplication.presentation.FoodPlatesViewModel
import com.test.myapplication.presentation.FoodViewModelFactory
import com.test.myapplication.repository.FoodPlateRepositoryImpl
import com.test.myapplication.repository.RetrofitClient
import com.test.myapplication.ui.foodPlatesList.adapter.FoodAdapter

class FoodListFragment : Fragment(R.layout.fragment_food_list), SearchView.OnQueryTextListener, FoodAdapter.CellClickListener {

    private lateinit var binding: FragmentFoodListBinding
    private lateinit var myAdapter: FoodAdapter

    private val viewModel by viewModels<FoodPlatesViewModel> {
        FoodViewModelFactory(
            FoodPlateRepositoryImpl(
                RemoteFoodPlatesDataSources(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoodListBinding.bind(view)

        viewModel.getRandomPlates().observe(viewLifecycleOwner,{ result ->
            when(result){
                is Resources.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resources.Success ->{
                    binding.progressBar.visibility = View.GONE
                    if (result.data.meals.isEmpty()){
                        return@observe
                    }else{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    binding.bannerPlate.load(result.data.meals[0].strMealThumb)
                }
                is Resources.Failure ->{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onCellClickListener(data: food_plates) {
        TODO("Not yet implemented")
    }

    private fun searchByName(query: String){
        viewModel.getResultsPlates().observe(viewLifecycleOwner,{ result ->
            when(result){
                is Resources.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resources.Success ->{
                    binding.progressBar.visibility = View.GONE
                    if (result.data.meals.isEmpty()){
                        return@observe
                    }else{
                        binding.emptyList.visibility = View.VISIBLE
                    }
                    val data = mutableListOf(result.data)
                    myAdapter = FoodAdapter(data, this)
                    binding.rvFoodPlates.adapter = myAdapter
                }
                is Resources.Failure ->{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}