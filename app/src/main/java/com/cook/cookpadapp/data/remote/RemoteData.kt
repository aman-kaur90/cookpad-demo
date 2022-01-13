package com.cook.cookpadapp.data.remote

import com.cook.cookpadapp.data.Resource
import com.cook.cookpadapp.data.models.recipes.Recipes
import com.cook.cookpadapp.data.models.recipes.RecipesItem
import com.cook.cookpadapp.data.models.recipes.TopRecipe
import com.cook.cookpadapp.data.models.recipes.TopRecipeItem
import com.cook.cookpadapp.data.error.NETWORK_ERROR
import com.cook.cookpadapp.data.error.NO_INTERNET_CONNECTION
import com.cook.cookpadapp.data.remote.service.RecipesService
import com.cook.cookpadapp.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Amanpreet Kaur
 */

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) :
    RemoteDataSource {

    override suspend fun requestRecipes(): Resource<Recipes> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        return when (val response = processCall(recipesService::fetchRecipes)) {
            is List<*> -> {
                Resource.Success(data = Recipes(response as ArrayList<RecipesItem>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun requestBestRecipes(): Resource<TopRecipe> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        return when (val response = processCall(recipesService::fetchTopRecipes)) {
            is List<*> -> {
                Resource.Success(data = TopRecipe(response as ArrayList<TopRecipeItem>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }


    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
