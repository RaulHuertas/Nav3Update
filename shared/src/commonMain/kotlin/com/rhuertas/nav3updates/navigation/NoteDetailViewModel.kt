package com.rhuertas.nav3updates.navigation
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf

class NoteDetailViewModel(
    private val noteId: String
) : ViewModel() {
    private val _noteState = MutableStateFlow(
        sampleNotes.first{it.id == noteId}
    )
    val noteState = _noteState.asStateFlow()
}

val NoteModule = module {
    viewModelOf(::NoteDetailViewModel)
}
