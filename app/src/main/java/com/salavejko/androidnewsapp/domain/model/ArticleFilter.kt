package com.salavejko.androidnewsapp.domain.model

sealed interface ArticleFilter {
    fun filter(article: Article): Boolean
}

data class TitleArticleFilter(val title: String) : ArticleFilter {
    override fun filter(article: Article): Boolean = article.title.contains(title)
}

data class RatingArticleFilter(val rating: Int) : ArticleFilter {
    override fun filter(article: Article): Boolean = article.rating > rating
}