package com.cook.cookpadapp.ui.component.recipes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.cook.cookpadapp.R
import com.cook.cookpadapp.databinding.HomeActivityBinding
import com.google.android.material.snackbar.Snackbar
import com.cook.cookpadapp.RECIPE_ITEM_KEY
import com.cook.cookpadapp.data.Resource
import com.cook.cookpadapp.data.models.recipes.Recipes
import com.cook.cookpadapp.data.models.recipes.RecipesItem
import com.cook.cookpadapp.data.models.recipes.TopRecipeItem
import com.cook.cookpadapp.base.BaseActivity
import com.cook.cookpadapp.utils.*
import com.cook.cookpadapp.ui.component.details.DetailsActivity
import com.cook.cookpadapp.ui.component.recipes.adapter.RecipesAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Amanpreet Kaur
 */
@AndroidEntryPoint
class RecipesListActivity : BaseActivity() {
    private lateinit var binding: HomeActivityBinding

    private val recipesListViewModel: RecipesListViewModel by viewModels()
    private lateinit var recipesAdapter: RecipesAdapter

    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.recipe)
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipesList.layoutManager = layoutManager
        binding.rvRecipesList.setHasFixedSize(true)
        recipesListViewModel.getRecipes()
        recipesListViewModel.getBestRecipes()
    }


    private fun bindListData(recipes: Recipes) {
        if (!(recipes.recipesList.isNullOrEmpty())) {
            recipesAdapter = RecipesAdapter(recipesListViewModel, recipes.recipesList)
            binding.rvRecipesList.adapter = recipesAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<RecipesItem>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(RECIPE_ITEM_KEY, it)
            }
            startActivity(nextScreenIntent)
        }
    }

    private fun bindTopHeader(item: TopRecipeItem) {
        binding.rlBestItem.visibility = View.VISIBLE
        binding.tvTopName.text = item.title
        binding.tvHeader.text = "Top Rated Receipe"
        if (item.preview_image_urls[0].isNotEmpty()) {
            Picasso.get().load(item.preview_image_urls[0]).placeholder(R.drawable.ic_icon)
                .error(R.drawable.ic_icon).into(binding.ivTopRecipeItemImage)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) GONE else VISIBLE
        binding.rvRecipesList.visibility = if (show) VISIBLE else GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvRecipesList.toGone()
    }

    private fun handleRecipesList(status: Resource<Recipes>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(recipes = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { recipesListViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(recipesListViewModel.recipesLiveData, ::handleRecipesList)
        observe(recipesListViewModel.openTopRecipeDetails, ::bindTopHeader)
        observeEvent(recipesListViewModel.openRecipeDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(recipesListViewModel.showSnackBar)
        observeToast(recipesListViewModel.showToast)

    }
}
