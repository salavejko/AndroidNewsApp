package com.salavejko.androidnewsapp.core.di

import com.salavejko.androidnewsapp.data.repository.ArticlesRepositoryImpl
import com.salavejko.androidnewsapp.data.source.assets.AssetsNewsSource
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
        assetsNewsSource: AssetsNewsSource,
    ): ArticlesRepository = ArticlesRepositoryImpl(assetsNewsSource)

}