package com.example.mynotescompose.view.data.repository

import com.example.mynotescompose.view.data.local.NoteDao
import com.example.mynotescompose.view.domain.model.Note
import com.example.mynotescompose.view.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dao: NoteDao
) : Repository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }
}
