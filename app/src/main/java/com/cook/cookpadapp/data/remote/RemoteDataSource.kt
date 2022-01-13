package com.cook.cookpadapp.data.remote

import com.cook.cookpadapp.data.Resource
import com.cook.cookpadapp.data.models.recipes.Recipes
import com.cook.cookpadapp.data.models.recipes.TopRecipe

/**
 * Created by Amanpreet Kaur
 */

internal interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Recipes>
    suspend fun requestBestRecipes(): Resource<TopRecipe>
}
