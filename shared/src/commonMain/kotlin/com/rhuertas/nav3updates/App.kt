package com.rhuertas.nav3updates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rhuertas.nav3updates.navigation.NavigationRoot
import com.rhuertas.nav3updates.navigation.NoteModule
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
@Preview
fun App() {

    KoinApplication(
        configuration = koinConfiguration {
            modules(NoteModule)
        },
    )
    {
        MaterialTheme {
            NavigationRoot(
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}