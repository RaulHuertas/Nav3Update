package com.rhuertas.nav3updates.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

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
    onNoteClick: (String) -> Unit,
) = { key: NavKey ->
    when (key) {
        is NoteListScreen -> {
            NavEntry<NavKey>(
                key = key,
            ) {
                NodeListScreenUI(
                    onNoteClick = onNoteClick,
                )
            }
        }

        is NoteDetailScreen -> {
            NavEntry<NavKey>(
                key = key,
            ) {
                NoteDetailScreenUI(
                    viewModel = koinViewModel {
                        parametersOf(key.id)
                    }
                )
            }
        }

        else -> error("Unknown NavKey: $key")
    }
}
