package org.kmp.newsapp.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import org.kmp.newsapp.data.model.Source

class SourceTypeConverter {
    @TypeConverter
    fun fromSourceToString(value: Source): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToSource(value: String): Source {
        return Json.decodeFromString(value)
    }
}