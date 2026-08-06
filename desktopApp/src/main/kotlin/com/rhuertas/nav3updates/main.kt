package com.rhuertas.nav3updates

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Nav3Updates",
    ) {
        App()
    }
}