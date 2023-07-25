package com.example.mynotescompose.view.presentation.note

import com.example.mynotescompose.view.domain.model.Note
import com.example.mynotescompose.view.domain.util.NoteOrder
import com.example.mynotescompose.view.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
