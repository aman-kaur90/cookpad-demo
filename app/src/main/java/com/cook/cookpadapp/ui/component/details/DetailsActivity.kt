package com.cook.cookpadapp.ui.component.details

import android.os.Bundle
import androidx.activity.viewModels
import com.cook.cookpadapp.R
import com.cook.cookpadapp.databinding.DetailsLayoutBinding
import com.squareup.picasso.Picasso
import com.cook.cookpadapp.RECIPE_ITEM_KEY
import com.cook.cookpadapp.data.models.recipes.RecipesItem
import com.cook.cookpadapp.base.BaseActivity
import com.cook.cookpadapp.utils.observe
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Amanpreet Kaur
 */

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var binding: DetailsLayoutBinding


    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initIntentData(intent.getParcelableExtra<RecipesItem>(RECIPE_ITEM_KEY))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun observeViewModel() {
        observe(viewModel.recipeData, ::initializeView)
    }

    private fun initializeView(recipesItem: RecipesItem) {
        binding.tvName.text = recipesItem.title
        binding.tvHeadline.text = recipesItem.title
        binding.tvDescription.text = recipesItem.story
        binding.tvUserName.text = recipesItem.user.name
        binding.tvIngrediants.text = viewModel.getIngrediants()
        binding.tvSteps.text = viewModel.getPreSteps()
        Picasso.get().load(recipesItem.user.image_url).placeholder(R.drawable.ic_avtar)
            .error(R.drawable.ic_avtar).into(binding.ivUserImage)

        Picasso.get().load(recipesItem.image_url).placeholder(R.drawable.ic_icon)
            .into(binding.ivRecipeImage)

    }
}
