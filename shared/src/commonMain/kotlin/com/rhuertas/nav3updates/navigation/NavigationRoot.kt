package com.rhuertas.nav3updates.navigation

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(configuration = config, NoteListScreen)
    val viewModel: NoteViewModel = koinViewModel()

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberViewModelStoreNavEntryDecorator(),
        ),
        sceneStrategies = listOf(
            TwoPaneSceneStrategy(currentWindowAdaptiveInfoV2().windowSizeClass),
            DialogSceneStrategy<NavKey>(),
        ),
        modifier = modifier,
        entryProvider = noteEntryProvider(
            viewModel = viewModel,
            onNoteClick = { noteId ->
                backStack.add(NoteDetailScreen(noteId))
            },

        ),
    )
}