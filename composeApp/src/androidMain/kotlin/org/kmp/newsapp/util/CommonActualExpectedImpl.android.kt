package org.kmp.newsapp.util

import android.app.Activity
import android.content.Intent
import java.util.UUID

actual fun getType(): Type {
    return Type.MOBILE
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String){
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