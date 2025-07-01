package com.salavejko.androidnewsapp.data.repository

import com.salavejko.androidnewsapp.data.model.PlaceholderColor
import com.salavejko.androidnewsapp.data.source.assets.AssetsNewsSource
import com.salavejko.androidnewsapp.domain.model.Article
import com.salavejko.androidnewsapp.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    val assetsArticlesSource: AssetsNewsSource
): ArticlesRepository {
    override suspend fun getArticles(): List<Article> {
        return assetsArticlesSource.readArticles().map {
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