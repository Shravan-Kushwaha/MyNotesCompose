package com.example.mynotescompose.view.presentation.add_edit_note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ColorBubble(modifier: Modifier, color: Color) {
    Box(
      modifier=  modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(color)
    )

}