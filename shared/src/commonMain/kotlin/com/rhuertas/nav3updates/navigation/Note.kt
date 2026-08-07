package com.rhuertas.nav3updates.navigation
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class Note (
    val id: String,
    val title : String,
    val content : String,
    val color : Color,
)

val sampleNotes = List(100) {
    Note(
        id = "$it",
        title = "Note #$it",
        content = "Content #$it",
        color = Color(Random.nextInt(), Random.nextInt(), Random.nextInt()),
    )
}