package com.rhuertas.nav3updates.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(configuration = config, NoteListScreen)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberViewModelStoreNavEntryDecorator(),
        ),
        modifier = modifier,

        entryProvider = { key ->
            when (key) {
                is NoteListScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        NodeListScreenUI(
                            onNoteClick = { noteId ->
                                backStack.add(NoteDetailScreen(noteId))
                            },
                        )
                    }
                }
                is NoteDetailScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        NoteDetailScreenUI(id = key.id)
                    }
                }
                else -> error("Unknown NavKey: $key")
            }
        }
    )
}