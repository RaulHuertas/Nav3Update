package com.rhuertas.nav3updates.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue

@Composable
fun NoteDetailScreenUI(
    //id: String,
    modifier: Modifier = Modifier,
    viewModel : NoteDetailViewModel = koinViewModel(),
) {
    val noteState by viewModel.noteState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier.
        fillMaxSize().
        background(color = noteState.color).
        padding(16.dp),
    ){
        Text(
            text = noteState.title,
            fontSize = 26.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = noteState.content,
            fontSize = 18.sp,
        )
    }

}