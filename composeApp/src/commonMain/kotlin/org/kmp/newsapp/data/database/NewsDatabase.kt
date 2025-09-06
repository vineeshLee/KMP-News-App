package org.kmp.newsapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.util.getDatabaseBuilder

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}