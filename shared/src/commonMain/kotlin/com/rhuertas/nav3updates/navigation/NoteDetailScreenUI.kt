package com.rhuertas.nav3updates.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NoteDetailScreenUI(
    modifier: Modifier = Modifier,
    //noteId: String,
    viewModel: NoteViewModel,
) {
    //LaunchedEffect(noteId) {
    //    viewModel.selectNote(noteId)
    //}

    val selectedNote by viewModel.selectedNote.collectAsStateWithLifecycle()
    //val noteState = selectedNote?.takeIf { it.id == noteId } ?: viewModel.noteById(noteId)
    //val noteState = selectedNote.
    if (selectedNote == null) {
        Text(
            text = "No note selected",
            fontSize = 18.sp,
            modifier = modifier.padding(16.dp)
        )
        return
    }
    val validNote = selectedNote ?: return
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = validNote.color)
            .padding(16.dp),
    ) {
        Text(
            text = validNote.title,
            fontSize = 26.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = validNote.content,
            fontSize = 18.sp,
        )
    }
}