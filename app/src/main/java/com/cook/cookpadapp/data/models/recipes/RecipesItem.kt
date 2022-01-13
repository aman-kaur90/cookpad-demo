package com.cook.cookpadapp.data.models.recipes


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
@JsonClass(generateAdapter = false)
@Parcelize
data class RecipesItem(
        @Json(name = "id")
        val id: String ="",
        @Json(name = "title")
        val title: String = "",
        @Json(name = "story")
        val story: String = "",
        @Json(name = "image_url")
        val image_url: String?= "",
        @Json(name = "ingredients")
        val ingredients: List<String> = listOf(),
        @Json(name = "published_at")
        val published_at: String = "",
        @Json(name = "user")
        val user: User ,
        @Json(name = "steps")
        val steps: List<Steps> = listOf()

) : Parcelable

@JsonClass(generateAdapter = false)
@Parcelize
data class User(  @Json(name = "name")
                  val name: String,
                  @Json(name = "image_url")
                  val image_url: String = ""): Parcelable
@JsonClass(generateAdapter = false)

@Parcelize
data class Steps(  @Json(name = "description")
                  val description: String,
                   @Json(name = "image_urls")
                   val image_urls: List<String> = listOf()): Parcelable

