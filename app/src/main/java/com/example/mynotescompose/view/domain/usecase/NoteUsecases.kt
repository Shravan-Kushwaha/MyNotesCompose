package com.example.mynotescompose.view.domain.usecase

class NoteUseCases(
    val getNotesUsecase: GetNotesUsecase,
    val deleteNoteUsecase: DeleteNoteUseCase,
    val addNoteUsecase: AddNoteUseCase,
    val getNoteByIdUsecase: GetNoteByIdUsecase
)