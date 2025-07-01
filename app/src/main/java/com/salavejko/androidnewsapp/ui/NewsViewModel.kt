package com.salavejko.androidnewsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salavejko.androidnewsapp.domain.model.Article
import com.salavejko.androidnewsapp.domain.model.ArticleFilter
import com.salavejko.androidnewsapp.domain.usecase.GetFilteredArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val getFilteredArticlesUseCase: GetFilteredArticlesUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    init {
        viewModelScope.launch {
            _articles.value = getFilteredArticlesUseCase.invoke()
        }
    }

    fun onButtonClicked(filters: List<ArticleFilter>) {
        viewModelScope.launch {
            _articles.value = getFilteredArticlesUseCase.invoke(filters)
        }
    }

}