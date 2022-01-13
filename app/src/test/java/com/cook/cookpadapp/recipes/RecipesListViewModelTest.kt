package com.cook.cookpadapp.recipes

import com.cook.cookpadapp.data.DataRepository
import com.cook.cookpadapp.ui.component.recipes.RecipesListViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipesListViewModelTest {
    private lateinit var recipesListViewModel: RecipesListViewModel
    private val dataRepository: DataRepository = mockk()


    @Before
    fun setUp() {
        // We initialise the repository with no tasks
    }

    @Test
    fun `get Recipes Empty List`() {
        recipesListViewModel = RecipesListViewModel(dataRepository)
        recipesListViewModel.getRecipes()
        recipesListViewModel.recipesLiveData.observeForever { }

        val isEmptyList = recipesListViewModel.recipesLiveData.value?.data?.recipesList.isNullOrEmpty()
        assertEquals(true, isEmptyList)
    }

}
