package org.kmp.newsapp.util

import coil3.Uri

expect fun getType(): Type
expect fun getRandomId(): String
expect fun shareLink(url: String)