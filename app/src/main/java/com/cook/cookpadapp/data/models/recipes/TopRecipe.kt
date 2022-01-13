package com.cook.cookpadapp.data.models.recipes

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class TopRecipe(val recipesList: ArrayList<TopRecipeItem>)

@Parcelize
data class TopRecipeItem(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "recipe_count")
    val recipe_count: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "preview_image_urls")
    val preview_image_urls: List<String> = listOf()
) : Parcelable