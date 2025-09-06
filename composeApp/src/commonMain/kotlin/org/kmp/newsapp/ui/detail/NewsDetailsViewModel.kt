package org.kmp.newsapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.data.repository.LocalNewsRepository

class NewsDetailsViewModel(
    val localNewsRepository: LocalNewsRepository
): ViewModel() {

    fun bookmarkArticle(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.upsertArticle(article)
        }
    }
}