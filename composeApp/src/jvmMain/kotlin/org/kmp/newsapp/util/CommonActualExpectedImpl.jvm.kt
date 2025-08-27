package org.kmp.newsapp.util

import java.util.UUID

actual fun getType(): Type {
   return Type.DESKTOP
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}