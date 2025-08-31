package org.kmp.newsapp.util

import platform.Foundation.NSUUID
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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