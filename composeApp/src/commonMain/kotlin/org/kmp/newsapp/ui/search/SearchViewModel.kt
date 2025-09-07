package org.kmp.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.data.model.ErrorResponse
import org.kmp.newsapp.data.model.NewsResponse
import org.kmp.newsapp.data.repository.NewsRepository
import org.kmp.newsapp.util.Resource

class SearchViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsState: StateFlow<Resource<List<Article>>> = _newsState

    fun performSearch(query:String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsState.emit(Resource.Loading)
            try {
                val response=newsRepository.searchNews(query)
                if (response.status.value in 200..299){
                    val body=response.body<NewsResponse>()
                    _newsState.emit(Resource.Success(body.articles))
                }else{
                    val error=response.body<ErrorResponse>()
                    _newsState.emit(Resource.Error(error.message))
                }
            } catch (e: Exception) {
                _newsState.emit(Resource.Error("Something went wrong"))
            }
        }
    }
}