package org.kmp.newsapp.di

import org.kmp.newsapp.ui.bookmark.BookMarkViewModel
import org.kmp.newsapp.ui.detail.NewsDetailsViewModel
import org.kmp.newsapp.ui.headline.HeadLineViewModel
import org.kmp.newsapp.ui.search.SearchViewModel
import org.kmp.newsapp.ui.setting.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HeadLineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookMarkViewModel)
    viewModelOf(::NewsDetailsViewModel)
    viewModelOf(::SettingsViewModel)
}