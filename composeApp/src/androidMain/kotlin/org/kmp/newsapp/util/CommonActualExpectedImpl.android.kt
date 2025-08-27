package org.kmp.newsapp.util

import java.util.UUID

actual fun getType(): Type {
    return Type.MOBILE
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}