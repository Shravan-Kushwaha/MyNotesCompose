package com.example.mynotescompose.view.domain.usecase

import com.example.mynotescompose.view.domain.model.Note
import com.example.mynotescompose.view.domain.repository.Repository
import com.example.mynotescompose.view.domain.util.InvalidNoteException

class AddNoteUseCase(private val repository: Repository) {

    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Title can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Content can't be empty.")
        }
        repository.insertNote(note)
    }
}