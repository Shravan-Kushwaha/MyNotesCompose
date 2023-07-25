package com.example.mynotescompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mynotescompose.view.presentation.add_edit_note.AddEditNoteScreen
import com.example.mynotescompose.view.presentation.note.NoteScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.NoteScreen.route) {
        composable(Screen.NoteScreen.route){ NoteScreen(navController) }
        composable(
            route = Screen.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(name = "noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(name = "noteColor") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){ entry ->
            val color = entry.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(navController, color)
        }
    }
}