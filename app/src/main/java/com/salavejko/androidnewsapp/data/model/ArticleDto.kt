package com.salavejko.androidnewsapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDto(
    val title: String,
    val description: String,
    @Json(name = "image_url")
    val imageUrl: String,
    val rating: Int,
    val placeholderColor: PlaceholderColor
)

@JsonClass(generateAdapter = true)
data class PlaceholderColor(
    val red: Int,
    val green: Int,
    val blue: Int
)