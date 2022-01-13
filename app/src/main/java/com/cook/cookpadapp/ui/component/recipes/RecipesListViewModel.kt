package com.cook.cookpadapp.ui.component.recipes

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cook.cookpadapp.data.DataRepositorySource
import com.cook.cookpadapp.data.Resource
import com.cook.cookpadapp.data.models.recipes.Recipes
import com.cook.cookpadapp.data.models.recipes.RecipesItem
import com.cook.cookpadapp.data.models.recipes.TopRecipeItem
import com.cook.cookpadapp.base.BaseViewModel
import com.cook.cookpadapp.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Amanpreet Kaur
 */
@HiltViewModel
class RecipesListViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipesLiveDataPrivate = MutableLiveData<Resource<Recipes>>()
    val recipesLiveData: LiveData<Resource<Recipes>> get() = recipesLiveDataPrivate

    private val openRecipeDetailsPrivate = MutableLiveData<SingleEvent<RecipesItem>>()
    val openRecipeDetails: LiveData<SingleEvent<RecipesItem>> get() = openRecipeDetailsPrivate


    private val topRecipesLiveDataPrivate = MutableLiveData<TopRecipeItem>()
    val openTopRecipeDetails: LiveData<TopRecipeItem> get() = topRecipesLiveDataPrivate

    /**
     * Error handling as UI
     */
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getRecipes() {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Resource.Loading()
                dataRepositoryRepository.requestRecipes().collect {
                    recipesLiveDataPrivate.value = it
                }
        }
    }

    fun getBestRecipes() {
        viewModelScope.launch {
            dataRepositoryRepository.requestBestRecipes().collect {
                topRecipesLiveDataPrivate.value= it.data?.recipesList?.get(0)
            }
        }
    }

    fun openRecipeDetails(recipe: RecipesItem) {
        openRecipeDetailsPrivate.value = SingleEvent(recipe)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }


}
