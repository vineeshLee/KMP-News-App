package org.kmp.newsapp.ui.headline

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import org.kmp.newsapp.util.categoryList

class HeadLineViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsState: StateFlow<Resource<List<Article>>> = _newsState
    var category by mutableStateOf(categoryList[0])

    init {
        getHeadLines()
    }

    fun getHeadLines() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsState.emit(Resource.Loading)
            try {
                val response=newsRepository.getAllNews(category = category)
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