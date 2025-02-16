package io.github.hoaithu842.spotlight_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform