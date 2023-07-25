package com.example.mynotescompose.view.presentation.note

import com.example.mynotescompose.view.domain.model.Note
import com.example.mynotescompose.view.domain.util.NoteOrder

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
    object RestoreNote : NoteEvent()
    object ToggleOrderSection : NoteEvent()
}
