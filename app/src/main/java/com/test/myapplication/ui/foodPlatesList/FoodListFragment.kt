package com.test.myapplication.ui.foodPlatesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.test.myapplication.R
import com.test.myapplication.core.Resources
import com.test.myapplication.data.model.MealList
import com.test.myapplication.data.remote.RemoteFoodPlatesDataSources
import com.test.myapplication.databinding.FragmentFoodListBinding
import com.test.myapplication.presentation.FoodPlatesViewModel
import com.test.myapplication.presentation.FoodViewModelFactory
import com.test.myapplication.repository.FoodPlateRepositoryImpl
import com.test.myapplication.repository.RetrofitClient
import com.test.myapplication.ui.foodPlatesList.adapter.FoodAdapter

class FoodListFragment : Fragment(R.layout.fragment_food_list), FoodAdapter.CellClickListener {

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

        viewModel.getRandomPlates().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resources.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resources.Success ->{
                    binding.progressBar.visibility = View.GONE
                    binding.bannerPlate.load(result.data.mealList[0].strMealThumb)
                }
                is Resources.Failure ->{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

        setupRecyclerView()
        searchView()
        setupObserverFood()
    }

    private fun setupObserverFood(){
        viewModel.getResultsPlates().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resources.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resources.Success ->{
                    binding.progressBar.visibility = View.GONE
                    binding.emptyList.visibility = View.GONE
                    val data = listOf(result.data)
                    myAdapter = FoodAdapter(requireContext(), data,this)
                    binding.rvFoodPlates.adapter = myAdapter
                }
                is Resources.Failure ->{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun setupRecyclerView(){
        binding.rvFoodPlates.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCellClickListener(meal: MealList) {
        /*val bundle = Bundle()
        bundle.putParcelable("meal",meal)*/
        findNavController().navigate(R.id.foodPlateDetailFragment)
    }

    private fun searchView() {
        binding.svFood.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setFood(newText!!)
                return false
            }
        })
    }
}