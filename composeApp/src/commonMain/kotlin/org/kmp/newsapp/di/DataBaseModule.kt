package org.kmp.newsapp.di

import org.kmp.newsapp.util.AppPreferences
import org.kmp.newsapp.util.dataStorePreference
import org.kmp.newsapp.util.getDatabaseBuilder
import org.kmp.newsapp.util.getRoomDataBase
import org.koin.dsl.module

val databaseModule = module {
    single {
        //database
        getRoomDataBase(getDatabaseBuilder())
    }
    //dataStore
    single {
        AppPreferences(dataStorePreference())
    }
}