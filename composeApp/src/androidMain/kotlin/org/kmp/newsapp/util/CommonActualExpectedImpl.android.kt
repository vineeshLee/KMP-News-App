package org.kmp.newsapp.util

import android.app.Activity
import android.content.Intent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import io.ktor.util.reflect.instanceOf
import org.kmp.newsapp.data.database.NewsDatabase
import java.util.*

actual fun getType(): Type {
    return Type.MOBILE
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Link")
    activityProvider.invoke().startActivity(shareIntent)
}

private var activityProvider: () -> Activity = {
    throw IllegalArgumentException("Error")
}

fun setActivityProvider(provider: () -> Activity) {
    activityProvider = provider
}

actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            activityProvider.invoke().filesDir.resolve(preferenceFileName).absolutePath
        })
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
   val context=activityProvider.invoke()
    val dbFile=context.getDatabasePath(DB_NAME)
    return Room.databaseBuilder<NewsDatabase>(
        context=context,
        name=dbFile.absolutePath
    )
}