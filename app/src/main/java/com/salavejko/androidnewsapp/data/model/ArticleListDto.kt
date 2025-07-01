package com.salavejko.androidnewsapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleListDto(
    val articles: List<ArticleDto>,
)