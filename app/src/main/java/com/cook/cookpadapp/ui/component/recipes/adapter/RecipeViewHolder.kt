package com.cook.cookpadapp.ui.component.recipes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.cook.cookpadapp.R
import com.cook.cookpadapp.databinding.RecipeItemBinding
import com.squareup.picasso.Picasso
import com.cook.cookpadapp.data.models.recipes.RecipesItem
import com.cook.cookpadapp.base.listeners.RecyclerItemListener

/**
 * Created by Amanpreet Kaur
 */

class RecipeViewHolder(private val itemBinding: RecipeItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipesItem: RecipesItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvName.text = recipesItem.title
        itemBinding.tvUserName.text = recipesItem.user.name
        if (!recipesItem.image_url.isNullOrEmpty()) {
            Picasso.get().load(recipesItem.image_url).placeholder(R.drawable.ic_icon)
                .error(R.drawable.ic_icon).into(itemBinding.ivRecipeItemImage)
        }
        if (recipesItem.user.image_url.isNotEmpty()) {
            Picasso.get().load(recipesItem.user.image_url).placeholder(R.drawable.ic_avtar)
                .error(R.drawable.ic_avtar).into(itemBinding.ivUserImage)
        }
        itemBinding.rlRecipeItem.setOnClickListener {
            recyclerItemListener.onItemSelected(
                recipesItem
            )
        }
    }
}

