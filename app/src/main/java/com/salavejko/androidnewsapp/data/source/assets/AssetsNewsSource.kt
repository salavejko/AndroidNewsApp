package com.salavejko.androidnewsapp.data.source.assets

import android.content.Context
import com.google.gson.Gson
import com.salavejko.androidnewsapp.data.model.ArticleDto
import com.salavejko.androidnewsapp.data.model.ArticleListDto
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.jvm.java

class AssetsNewsSource @Inject constructor(
    @ApplicationContext val context: Context,
    @Named("IODispatcher")
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun readArticles(): List<ArticleDto> = with(ioDispatcher) {
        val json = context.assets.open(ARTICLES_SOURCE).bufferedReader().use { it.readText() }
        return Gson().fromJson(json, ArticleListDto::class.java).articles
    }

    companion object {
        const val ARTICLES_SOURCE = "articles.json"
    }
}