package com.salavejko.androidnewsapp.domain.model

data class Article(
    val title: String,
    val description: String,
    val imageUrl: String,
    val rating: Int,
    val placeholderColor: Int
)