package com.salavejko.androidnewsapp.data.source.local

import android.content.Context
import com.salavejko.androidnewsapp.data.model.ArticleDto
import com.salavejko.androidnewsapp.data.model.ArticleListDto
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.jvm.java

class AssetsNewsSource @Inject constructor(
    @ApplicationContext val context: Context,
    @Named("IODispatcher")
    private val ioDispatcher: CoroutineDispatcher,
): NewsDataSource {

    override suspend fun getArticles(): List<ArticleDto> = withContext(ioDispatcher) {
        val json = context.assets.open(ARTICLES_SOURCE).bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(ArticleListDto::class.java)
        adapter.fromJson(json)?.articles ?: emptyList()
    }

    companion object {
        const val ARTICLES_SOURCE = "articles.json"
    }
}