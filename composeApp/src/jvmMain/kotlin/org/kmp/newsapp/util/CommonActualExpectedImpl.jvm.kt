package org.kmp.newsapp.util

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.util.UUID

actual fun getType(): Type {
   return Type.DESKTOP
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}