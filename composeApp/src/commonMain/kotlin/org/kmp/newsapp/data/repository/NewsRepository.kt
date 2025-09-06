package org.kmp.newsapp.data.repository

import io.ktor.client.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import org.kmp.newsapp.util.API_KEY

class NewsRepository(
    private val httpClient: HttpClient
) {


    suspend fun getAllNews(category: String): HttpResponse{
            return httpClient.get{
                url("top-headlines")
                parameter("category",category)
                parameter("apiKey", API_KEY)
            }
    }

    suspend fun searchNews(query: String): HttpResponse{
        return httpClient.get{
            url("everything")
            parameter("q",query)
            parameter("apiKey", API_KEY)
        }
    }
}