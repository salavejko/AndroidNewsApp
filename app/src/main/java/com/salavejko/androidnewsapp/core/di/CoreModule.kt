package com.salavejko.androidnewsapp.core.di

import com.salavejko.androidnewsapp.data.repository.ArticlesRepositoryImpl
import com.salavejko.androidnewsapp.data.source.local.AssetsNewsSource
import com.salavejko.androidnewsapp.data.source.local.NewsDataSource
import com.salavejko.androidnewsapp.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    @Named("IODispatcher")
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideArticlesRepository(
        @Named("AssetsNewsSource")
        newsDataSource: NewsDataSource,
    ): ArticlesRepository = ArticlesRepositoryImpl(newsDataSource)

    @Provides
    @Named("AssetsNewsSource")
    fun provideNewsSource(assetsNewsSource: AssetsNewsSource): NewsDataSource = assetsNewsSource

    //TODO we can add different sources here e.g. remote, mock, assets or local
}