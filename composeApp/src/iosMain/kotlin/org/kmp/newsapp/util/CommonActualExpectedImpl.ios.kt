@file:Suppress("UNCHECKED_CAST")

package org.kmp.newsapp.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import org.kmp.newsapp.data.database.NewsDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSURL
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import data.database.instantiateImpl

actual fun getType(): Type {
    return Type.MOBILE
}


actual fun getRandomId(): String {
    return NSUUID.UUID().toString()
}

actual fun shareLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}

actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            // 1. Get the list of URLs
            val urls: List<NSURL> = NSFileManager.defaultManager.URLsForDirectory(
                directory = NSDocumentDirectory,
                inDomains = NSUserDomainMask
            ) as? List<NSURL> ?: listOf() // Cast to List<NSURL> and provide an empty list if null

            // 2. Safely get the first URL
            val documentDirectory: NSURL? = urls.firstOrNull()

            // 3. Require it to be non-null and construct the path
            requireNotNull(documentDirectory) {
                "Could not find Document directory."
            }.path + "/$preferenceFileName"
        }
    )
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/${DB_NAME}" // Assuming DB_NAME is defined elsewhere
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory = { NewsDatabase::class.instantiateImpl() } // Use the generated instantiateImpl()
    )
}