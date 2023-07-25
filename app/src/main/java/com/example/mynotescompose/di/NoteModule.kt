package com.example.mynotescompose.di

import android.app.Application
import androidx.room.Room
import com.example.mynotescompose.view.data.local.NoteDatabase
import com.example.mynotescompose.view.data.local.NoteDatabase.Companion.DATABASE_NAME
import com.example.mynotescompose.view.data.repository.RepositoryImpl
import com.example.mynotescompose.view.domain.repository.Repository
import com.example.mynotescompose.view.domain.usecase.AddNoteUseCase
import com.example.mynotescompose.view.domain.usecase.DeleteNoteUseCase
import com.example.mynotescompose.view.domain.usecase.GetNoteByIdUsecase
import com.example.mynotescompose.view.domain.usecase.GetNotesUsecase
import com.example.mynotescompose.view.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = NoteDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: NoteDatabase): Repository {
        return RepositoryImpl(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: Repository): NoteUseCases {
        return NoteUseCases(
            getNotesUsecase = GetNotesUsecase(repository),
            deleteNoteUsecase = DeleteNoteUseCase(repository),
            addNoteUsecase = AddNoteUseCase(repository),
            getNoteByIdUsecase = GetNoteByIdUsecase(repository)
        )
    }
}