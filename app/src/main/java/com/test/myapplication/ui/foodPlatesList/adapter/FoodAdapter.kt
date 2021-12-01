package com.test.myapplication.ui.foodPlatesList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.myapplication.R
import com.test.myapplication.core.BaseViewHolder
import com.test.myapplication.data.model.MealList

class FoodAdapter(
   private val context: Context,
   private val dataSet: List<MealList>,
   private val cellClickListener: CellClickListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface CellClickListener {
        fun onCellClickListener(meal: MealList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return FoodViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.view_holder_food_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is FoodViewHolder -> holder.bind(dataSet[position],position)
        }
    }

    inner class FoodViewHolder(itemView: View): BaseViewHolder<MealList>(itemView){
        override fun bind(item: MealList, position: Int) {
            val images: ImageView = itemView.findViewById(R.id.image_food)
            val nameFood: TextView = itemView.findViewById(R.id.name_food)
            val categoryFood: TextView = itemView.findViewById(R.id.category_food)

            images.load(item.mealList[0].strMealThumb)
            nameFood.text = item.mealList[0].strMeal
            categoryFood.text = item.mealList[0].strCategory
            itemView.setOnClickListener { cellClickListener.onCellClickListener(item) }
        }
    }
}