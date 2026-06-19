package com.onurcan.core.ui.bean

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SavableEditBox(
    modifier: Modifier = Modifier,
    currentText: String = "",
    isTitle: Boolean = false,
    onSave: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var textInput by remember(currentText) { mutableStateOf(currentText) }
    AnimatedContent(
        targetState = isEditing,
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith
                    fadeOut(animationSpec = tween(300))
        },
        label = "SavableEditBoxTransition"
    ) { editing ->
        if (!editing) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isEditing = true }
            ) {
                Text(
                    modifier = modifier,
                    text = textInput,
                    style = if (isTitle) MaterialTheme.typography.titleLarge else MaterialTheme.typography.bodyLarge,
                    fontWeight = if (isTitle) FontWeight.Bold else FontWeight.Normal,
                    color = if (textInput.isEmpty()) MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.6f
                    )
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            Card {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Row {
                        OutlinedTextField(
                            value = textInput,
                            onValueChange = { textInput = it },
                            modifier = Modifier
                                .weight(1f),
                            placeholder = { Text("Write your story here...") },
                            shape = RoundedCornerShape(12.dp)
                        )
                        IconButton(
                            onClick = { isEditing = false }
                        ) {
                            Icon(
                                painter = rememberVectorPainter(Icons.Filled.Cancel),
                                contentDescription = null
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            onSave(textInput)
                            isEditing = false
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Save Changes")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SavableEditBoxPreview() {
    SavableEditBox(currentText = "asd", onSave = {})
}