package com.salavejko.androidnewsapp.domain.repository

import com.salavejko.androidnewsapp.domain.model.Article

interface ArticlesRepository {

    suspend fun getArticles(): List<Article>
}