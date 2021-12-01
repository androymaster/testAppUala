package com.test.myapplication.ui.foodPlatesList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.myapplication.R
import com.test.myapplication.data.model.food_plates

class FoodAdapter(
   private val dataSet: List<food_plates>,
   private val cellClickListener: CellClickListener
): RecyclerView.Adapter<FoodAdapter.FoodViewModel>() {

    interface CellClickListener {
        fun onCellClickListener(data: food_plates)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_food_item, parent, false)
        return FoodViewModel(view)
    }

    override fun onBindViewHolder(holder: FoodViewModel, position: Int) {
        val data = dataSet[position]
        val name = data.meals[0].strMeal
        val category = data.meals[0].strCategory
        holder.images.load(data.meals[0].strMealThumb)
        holder.nameFood.text = name
        holder.categoryFood.text = category

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
    }

    class FoodViewModel(view: View): RecyclerView.ViewHolder(view){
        val images: ImageView = view.findViewById(R.id.image_food)
        val nameFood: TextView = view.findViewById(R.id.name_food)
        val categoryFood: TextView = view.findViewById(R.id.category_food)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}