package com.cook.cookpadapp.base.listeners

import com.cook.cookpadapp.data.models.recipes.RecipesItem

/**
 * Created by Amanpreet Kaur
 */

interface RecyclerItemListener {
    fun onItemSelected(recipe : RecipesItem)
}
