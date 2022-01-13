package com.cook.cookpadapp.data

import com.cook.cookpadapp.data.models.recipes.Recipes
import com.cook.cookpadapp.data.models.recipes.TopRecipe
import kotlinx.coroutines.flow.Flow

/**
 * Created by Amanpreet Kaur
 */

interface DataRepositorySource {
    suspend fun requestRecipes(): Flow<Resource<Recipes>>
    suspend fun requestBestRecipes(): Flow<Resource<TopRecipe>>

}
