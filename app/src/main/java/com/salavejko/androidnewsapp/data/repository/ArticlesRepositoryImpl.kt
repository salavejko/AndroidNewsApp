package com.salavejko.androidnewsapp.data.repository

import com.salavejko.androidnewsapp.data.model.PlaceholderColor
import com.salavejko.androidnewsapp.data.source.local.NewsDataSource
import com.salavejko.androidnewsapp.domain.model.Article
import com.salavejko.androidnewsapp.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    val dataSource: NewsDataSource,
    //val local: NewsLocalSource todo Example of local source that can be added later
): ArticlesRepository {
    override suspend fun getArticles(): List<Article> {
        return dataSource.getArticles().map {
            Article(
                it.title,
                it.description,
                it.imageUrl,
                placeholderColor = it.placeholderColor.toColorInt(),
                rating = it.rating,
            )
        }
    }
}

private fun PlaceholderColor.toColorInt(): Int {
    return (255 shl 24) or (red shl 16) or (green shl 8) or blue
}