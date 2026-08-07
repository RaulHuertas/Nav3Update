package com.rhuertas.nav3updates.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NodeListScreenUI(
    modifier : Modifier = Modifier,
    onNoteClick : (String) -> Unit
    ) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(sampleNotes, key={it.id}){ note ->

            Column(
                modifier = Modifier.fillMaxSize()
                    .background(color = note.color)
                    .clickable {
                        onNoteClick(note.id)
                    }
                ,
            ){
                Text(
                    text = note.title,
                    fontSize = 18.sp,
                )
                Text(
                    text = note.content
                )
            }
        }
    }
}