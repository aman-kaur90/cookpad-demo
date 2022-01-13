package com.cook.cookpadapp.ui.component.details

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cook.cookpadapp.base.BaseViewModel
import com.cook.cookpadapp.data.DataRepositorySource
import com.cook.cookpadapp.data.models.recipes.RecipesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Amanpreet Kaur
 */
@HiltViewModel
open class DetailsViewModel @Inject constructor(private val dataRepository: DataRepositorySource) :
    BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipePrivate = MutableLiveData<RecipesItem>()
    val recipeData: LiveData<RecipesItem> get() = recipePrivate


    fun initIntentData(recipe: RecipesItem?) {
        recipePrivate.value = recipe
    }

    fun getIngrediants(): String {
        val list = recipePrivate.value?.ingredients
        val sb = StringBuilder()
        if (list != null) {
            var i = 0
            for (str in list) {
                i += 1
                sb.append(i)
                sb.append(str)
                sb.append("\n")
            }
        }
        return sb.toString()
    }

    fun getPreSteps(): String {
        val list = recipePrivate.value?.steps
        val sb = StringBuilder()
        if (list != null) {
            for (item in list) {
                sb.append("-> ")
                sb.append(item.description)
                sb.append("\n")
            }
        }
        return sb.toString()
    }

}
