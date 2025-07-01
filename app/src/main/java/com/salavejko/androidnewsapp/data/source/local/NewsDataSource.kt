package com.salavejko.androidnewsapp.data.source.local

import com.salavejko.androidnewsapp.data.model.ArticleDto

interface NewsDataSource {
    suspend fun getArticles(): List<ArticleDto>
}