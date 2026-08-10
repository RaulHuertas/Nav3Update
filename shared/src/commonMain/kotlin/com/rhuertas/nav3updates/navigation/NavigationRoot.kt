package com.rhuertas.nav3updates.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.*
import kotlinx.serialization.Serializable
import androidx.compose.runtime.remember
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.compose.LocalSavedStateRegistryOwner
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import nav3updates.shared.generated.resources.Res
import nav3updates.shared.generated.resources.app_name

@Serializable
data object NoteListScreen : NavKey
@Serializable
data class NoteDetailScreen(val id : String):NavKey

private val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(NoteListScreen::class, NoteListScreen.serializer())
            subclass(NoteDetailScreen::class, NoteDetailScreen.serializer())
        }
    }
}

@Composable
fun NavigationRoot(
    modifier : Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(configuration=config, NoteListScreen)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberViewModelStoreNavEntryDecorator(),
        ),
        modifier = modifier,

        entryProvider = { key ->
            when(key){
                is  NoteListScreen -> {
                    NavEntry(
                        key=key,
                    ){
                        NodeListScreenUI(
                            onNoteClick = {noteId ->
                                backStack.add(NoteDetailScreen(noteId))
                            }
                        )
                    }
                }
                is NoteDetailScreen -> {
                    NavEntry(
                        key=key,
                    ){
                        NoteDetailScreenUI(id = key.id)
                    }
                }
                else -> error("Unknown NavKey: $key")
            }
        }
    )


}