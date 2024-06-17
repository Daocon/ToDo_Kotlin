package com.daocon.todo_kotlin.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.daocon.todo_kotlin.feature_todo.domain.model.TodoItem

data class TodoItemColors(
    val backGroundColor: Color,
    val textColor: Color,
    val archiveIconColor: Color,
    val checkColor: Color
)

@Composable
fun getTodoColor(todo: TodoItem): TodoItemColors {
    return if (todo.archived) {
        TodoItemColors(
            backGroundColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
            textColor = MaterialTheme.colorScheme.onSecondary,
            archiveIconColor = MaterialTheme.colorScheme.onSecondary,
            checkColor = if(todo.completed) MaterialTheme.colorScheme.tertiaryContainer
                else MaterialTheme.colorScheme.onSecondary
        )
    } else {
        TodoItemColors(
            backGroundColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            archiveIconColor = MaterialTheme.colorScheme.secondary,
            checkColor = if(todo.completed) MaterialTheme.colorScheme.tertiaryContainer
            else MaterialTheme.colorScheme.secondary
        )
    }
}