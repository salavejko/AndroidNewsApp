package com.salavejko.androidnewsapp.domain.usecase

import com.salavejko.androidnewsapp.domain.model.Article
import com.salavejko.androidnewsapp.domain.model.ArticleFilter
import com.salavejko.androidnewsapp.domain.repository.ArticlesRepository
import jakarta.inject.Inject

class GetFilteredArticlesUseCase @Inject constructor(val repository: ArticlesRepository) {
    suspend fun invoke(filters: List<ArticleFilter> = emptyList()): List<Article>  {
        return repository.getArticles().filter { article ->
            filters.all { it.filter(article) }
        }
    }
}