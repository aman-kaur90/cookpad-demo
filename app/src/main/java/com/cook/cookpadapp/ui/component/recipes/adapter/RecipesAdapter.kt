package com.cook.cookpadapp.ui.component.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cook.cookpadapp.databinding.RecipeItemBinding
import com.cook.cookpadapp.data.models.recipes.RecipesItem
import com.cook.cookpadapp.base.listeners.RecyclerItemListener
import com.cook.cookpadapp.ui.component.recipes.RecipesListViewModel

/**
 * Created by Amanpreet Kaur
 */

class RecipesAdapter(private val recipesListViewModel: RecipesListViewModel, private val recipes: List<RecipesItem>) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: RecipesItem) {
            recipesListViewModel.openRecipeDetails(recipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}

