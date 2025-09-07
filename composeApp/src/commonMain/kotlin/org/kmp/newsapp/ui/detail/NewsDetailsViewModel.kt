package org.kmp.newsapp.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.data.repository.LocalNewsRepository

class NewsDetailsViewModel(
    val localNewsRepository: LocalNewsRepository
) : ViewModel() {

    var isBookMarked by mutableStateOf(false)
    fun bookmarkArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            when {
                isBookMarked.not() -> {
                    localNewsRepository.upsertArticle(article)
                }
                else -> {
                    localNewsRepository.deleteArticle(article)
                }
            }
            isBookMarked=!isBookMarked
        }
    }

    fun checkIsBookMarked(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.getArticle(article.publishedAt)?.let {
                isBookMarked = true
            }
        }
    }

}