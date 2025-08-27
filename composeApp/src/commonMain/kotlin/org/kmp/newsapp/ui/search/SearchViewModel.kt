package org.kmp.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.util.Resource
import org.kmp.newsapp.util.articles

class SearchViewModel : ViewModel() {
    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsState: StateFlow<Resource<List<Article>>> = _newsState

    fun performSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsState.emit(Resource.Loading)
            try {
                _newsState.emit(Resource.Success(articles))
            } catch (e: Exception) {
                _newsState.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}