package com.rhuertas.nav3updates.navigation

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.metadata
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
data object NoteListScreen : NavKey

@Serializable
data class NoteDetailScreen(val id: String) : NavKey

internal val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(NoteListScreen::class, NoteListScreen.serializer())
            subclass(NoteDetailScreen::class, NoteDetailScreen.serializer())
        }
    }
}

internal fun noteEntryProvider(
    viewModel: NoteViewModel,
    onNoteClick: (String) -> Unit,
) = { key: NavKey ->
    when (key) {
        is NoteListScreen -> {
            NavEntry<NavKey>(
                key = key,
                metadata = TwoPaneScene.twoPane()
            ) {
                NoteListScreenUI(
                    viewModel = viewModel,
                    onNoteClick = { noteId ->
                        viewModel.selectNote(noteId)
                        onNoteClick(noteId)
                    },
                )
            }
        }

        is NoteDetailScreen -> {
            NavEntry<NavKey>(
                key = key,
                metadata = TwoPaneScene.twoPane()
            ) {
                NoteDetailScreenUI(
                    noteId = key.id,
                    viewModel = viewModel,
                )
            }
        }

        else -> error("Unknown NavKey: $key")
    }
}
