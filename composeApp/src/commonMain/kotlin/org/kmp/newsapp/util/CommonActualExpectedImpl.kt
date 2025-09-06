package org.kmp.newsapp.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.utils.io.*
import io.ktor.utils.io.locks.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import okio.Path.Companion.toPath
import org.kmp.newsapp.data.database.NewsDatabase

expect fun getType(): Type
expect fun getRandomId(): String
expect fun shareLink(url: String)

expect fun dataStorePreference(): DataStore<Preferences>
expect fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase>
fun getRoomDataBase(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

object AppSettings {
    private lateinit var dataStore: DataStore<Preferences>

    @OptIn(InternalAPI::class)
    private val lock = SynchronizedObject()

    @OptIn(InternalAPI::class)
    fun getDataStore(producerPath: () -> String): DataStore<Preferences> {
        return synchronized(lock) {
            if (::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = { producerPath().toPath() }
                ).also { dataStore = it }
            }
        }
    }
}