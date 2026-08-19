package com.rhuertas.nav3updates.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NoteListScreenUI(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel,
    onNoteClick: (String) -> Unit,
) {
    val scrollIndex by viewModel.scrollIndex.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = scrollIndex)

    LaunchedEffect(lazyListState.firstVisibleItemIndex) {
        viewModel.onScroll(lazyListState.firstVisibleItemIndex)
    }

    LazyColumn(
        state = lazyListState,
        modifier = modifier.fillMaxSize(),
    ) {
        items(viewModel.notes, key = { it.id }) { note ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = note.color)
                    .clickable {
                        onNoteClick(note.id)
                    },
            ) {
                Text(
                    text = note.title,
                    fontSize = 18.sp,
                )
                Text(
                    text = note.content,
                )
            }
        }
    }
}
