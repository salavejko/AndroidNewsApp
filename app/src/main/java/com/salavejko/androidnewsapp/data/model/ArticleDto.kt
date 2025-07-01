package com.salavejko.androidnewsapp.data.model

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    val title: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val rating: Int,
    val placeholderColor: PlaceholderColor
)

data class PlaceholderColor(
    val red: Int,
    val green: Int,
    val blue: Int
)