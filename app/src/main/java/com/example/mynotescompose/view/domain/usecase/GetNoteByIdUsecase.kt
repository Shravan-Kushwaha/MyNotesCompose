package com.example.mynotescompose.view.domain.usecase

import com.example.mynotescompose.view.domain.model.Note
import com.example.mynotescompose.view.domain.repository.Repository

class GetNoteByIdUsecase(private val repository: Repository) {
    suspend operator fun invoke(noteId: Int): Note? {
        return repository.getNoteById(noteId)
    }
}