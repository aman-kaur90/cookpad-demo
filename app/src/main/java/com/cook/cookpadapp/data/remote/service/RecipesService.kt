package com.cook.cookpadapp.data.remote.service

import com.cook.cookpadapp.data.models.recipes.RecipesItem
import com.cook.cookpadapp.data.models.recipes.TopRecipeItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Amanpreet Kaur
 */

interface RecipesService {
    @GET("api/recipes")
    suspend fun fetchRecipes(): Response<List<RecipesItem>>
    @GET("api/collections")
    suspend fun fetchTopRecipes(): Response<List<TopRecipeItem>>
    @GET("api/recipes/{id}")
    suspend fun fetchRecipeDetails(@Path("id")id:String?): Response<RecipesItem>
}
