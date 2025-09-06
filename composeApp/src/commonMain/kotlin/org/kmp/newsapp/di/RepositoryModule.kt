package org.kmp.newsapp.di

import org.kmp.newsapp.data.database.NewsDatabase
import org.kmp.newsapp.data.repository.LocalNewsRepository
import org.kmp.newsapp.data.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        NewsRepository(get())
    }

    single {
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }
}