package com.rhuertas.nav3updates.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun NodeListScreenUI(modifier : Modifier = Modifier,) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(100) { index ->
            Text("Note $index")
        }
    }
}