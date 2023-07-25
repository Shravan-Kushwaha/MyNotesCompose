package com.example.mynotescompose.view.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mynotescompose.view.domain.model.Note
import com.example.mynotescompose.view.presentation.add_edit_note.components.ColorBubble
import com.example.mynotescompose.view.presentation.add_edit_note.components.TransparentHintTextField
import com.example.mynotescompose.view.presentation.util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    navigator: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val noteTitle = viewModel.noteTitle
    val noteContent = viewModel.noteContent
    val scaffoldState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val backgroundAnimatable = remember {
        Animatable(
            Color((if (noteColor != -1) noteColor else viewModel.noteColor.value))
        )
    }
    LaunchedEffect(key1 = true) {
        // handle navigation and showing snack bar
        viewModel.uiEventFlow.collectLatest { event ->
            when (event) {

                is UiEvent.NavigateOnSaveNote -> {
                    navigator.navigateUp()
                }

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.showSnackbar(
                        message = event.message
                    )
                }

                else -> {}
            }
        }

    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = scaffoldState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditNoteEvent.SaveNote) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .background(backgroundAnimatable.value)
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { itemColor ->
                    ColorBubble(
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    backgroundAnimatable.animateTo(
                                        targetValue = itemColor,
                                        animationSpec = tween(500)
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(itemColor.toArgb()))
                            }
                            .border(
                                width = 2.dp,
                                color = if (viewModel.noteColor.value == itemColor.toArgb()) Color.DarkGray else Color.Transparent,
                                shape = CircleShape
                            ), color = itemColor
                    )
                }
            }
            /* LazyRow(
                 modifier = Modifier
                     .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
             ) {
                 items(noteColors) { item ->
                     when (item) {
                         RedOrange -> {
                             ColorBubble(color = item, "White") {
                                 backcolor = item
                             }
                         }

                         LightGreen -> {
                             ColorBubble(color = item, "Magenta") {
                                 backcolor = item
                             }
                         }

                         Violet -> {
                             ColorBubble(color = item, "Green") {
                                 backcolor = item
                             }
                         }

                         BabyBlue -> {
                             ColorBubble(color = item, "Cyan") {
                                 backcolor = item
                             }
                         }

                         RedPink -> {
                             ColorBubble(color = item, "Yellow") {
                                 backcolor = item
                             }
                         }
                     }
                 }


             }*/
//jjak
            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = noteTitle.value.text,
                hint = noteTitle.value.hint,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
                onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it)) },
                singleLine = true,
                isHintVisible = noteTitle.value.isHintVisible,
                textStyle = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                modifier = Modifier.fillMaxHeight(),
                text = noteContent.value.text,
                hint = noteContent.value.hint,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) },
                onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it)) },
                isHintVisible = noteContent.value.isHintVisible,
                textStyle = MaterialTheme.typography.bodyLarge,
            )
        }
    }

}