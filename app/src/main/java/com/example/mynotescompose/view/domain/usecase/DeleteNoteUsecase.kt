package com.example.mynotescompose.view.domain.usecase

import com.example.mynotescompose.view.domain.model.Note
import com.example.mynotescompose.view.domain.repository.Repository

class DeleteNoteUseCase(private val repository: Repository) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}