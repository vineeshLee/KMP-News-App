package org.kmp.newsapp.util

import platform.Foundation.NSUUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

actual fun getType(): Type {
    return Type.MOBILE
}


actual fun getRandomId(): String {
    return NSUUID.UUID().toString()
}