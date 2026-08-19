package com.rhuertas.nav3updates.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteListViewModel : ViewModel() {
    private val _scrollIndex = MutableStateFlow(0)
    val scrollIndex = _scrollIndex.asStateFlow()

    val notes = sampleNotes

    fun onScroll(index: Int) {
        _scrollIndex.value = index
    }
}
