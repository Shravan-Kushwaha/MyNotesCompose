package com.example.mynotescompose.view.domain.repository

import com.example.mynotescompose.view.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun deleteNote(note: Note)

    suspend fun insertNote(note: Note)
}