package com.cook.cookpadapp.data

import com.cook.cookpadapp.data.models.recipes.Recipes
import com.cook.cookpadapp.data.models.recipes.TopRecipe
import com.cook.cookpadapp.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by Amanpreet Kaur
 */

class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val ioDispatcher: CoroutineContext) :
    DataRepositorySource {

    override suspend fun requestRecipes(): Flow<Resource<Recipes>> {
        return flow {
            emit(remoteRepository.requestRecipes())
        }.flowOn(ioDispatcher)
    }

    override suspend fun requestBestRecipes(): Flow<Resource<TopRecipe>> {
        return flow {
            emit(remoteRepository.requestBestRecipes())
        }.flowOn(ioDispatcher)
    }




}
