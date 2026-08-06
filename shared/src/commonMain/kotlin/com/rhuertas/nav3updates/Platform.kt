package com.rhuertas.nav3updates

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform