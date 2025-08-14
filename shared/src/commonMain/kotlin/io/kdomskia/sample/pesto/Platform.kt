package io.kdomskia.sample.pesto

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform