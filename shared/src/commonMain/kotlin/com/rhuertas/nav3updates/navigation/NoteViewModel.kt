package com.rhuertas.nav3updates.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class NoteViewModel : ViewModel() {
    private val _scrollIndex = MutableStateFlow(0)
    val scrollIndex = _scrollIndex.asStateFlow()

    private val _selectedNote = MutableStateFlow<Note?>(null)
    val selectedNote = _selectedNote.asStateFlow()

    val notes = sampleNotes

    fun onScroll(index: Int) {
        _scrollIndex.value = index
    }

    fun selectNote(noteId: String) {
        _selectedNote.value = noteById(noteId)
    }

    fun noteById(noteId: String): Note {
        return notes.first { it.id == noteId }
    }
}

val NoteModule = module {
    viewModelOf(::NoteViewModel)
}
