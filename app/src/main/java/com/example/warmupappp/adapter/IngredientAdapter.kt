package com.example.warmupappp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warmupappp.R
import com.example.warmupappp.model.Ingredient

class IngredientAdapter(private val ingredients: List<Ingredient>) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewQuantity: TextView = itemView.findViewById(R.id.textViewQuantity)
        val textViewIngredient: TextView = itemView.findViewById(R.id.textViewIngredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.textViewQuantity.text = ingredient.quantity
        holder.textViewIngredient.text = ingredient.ingredient
    }

    override fun getItemCount(): Int = ingredients.size
}
