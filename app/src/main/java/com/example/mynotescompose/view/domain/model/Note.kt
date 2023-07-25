package com.example.mynotescompose.view.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynotescompose.ui.theme.BabyBlue
import com.example.mynotescompose.ui.theme.LightGreen
import com.example.mynotescompose.ui.theme.RedOrange
import com.example.mynotescompose.ui.theme.RedPink
import com.example.mynotescompose.ui.theme.Violet

@Entity(tableName = Note.NOTE_TABLE)
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val color: Int,
    val timeStamp: Long
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
        const val NOTE_TABLE = "note"
    }
}