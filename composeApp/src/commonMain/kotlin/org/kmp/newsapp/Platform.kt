package org.kmp.newsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform