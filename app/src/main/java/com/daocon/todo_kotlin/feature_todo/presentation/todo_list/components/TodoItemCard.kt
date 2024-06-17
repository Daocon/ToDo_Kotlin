package com.daocon.todo_kotlin.feature_todo.presentation.todo_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daocon.todo_kotlin.core.presentation.components.ArchiveButton
import com.daocon.todo_kotlin.core.presentation.components.CompleteButtons
import com.daocon.todo_kotlin.core.presentation.components.DeleteButton
import com.daocon.todo_kotlin.core.presentation.components.getTodoColor
import com.daocon.todo_kotlin.feature_todo.domain.model.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemCard(
    todo: TodoItem,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onCompleteClick: () -> Unit,
    onArchiveClick: () -> Unit,
    onCardClick: () -> Unit
) {
    val todoColors = getTodoColor(todo = todo)

    Card(
        onClick = onCardClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = todoColors.backGroundColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            CompleteButtons(onCompleteClick, todoColors.checkColor, todo.completed)
            Text(
                text = todo.title,
                style = MaterialTheme.typography.headlineLarge,
                color = todoColors.textColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = todo.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = todoColors.textColor,
                    fontSize = 24.sp,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(end = 4.dp)
            ) {
                ArchiveButton(onArchiveClick, todoColors.archiveIconColor)
                DeleteButton(onDeleteClick)
            }
        }
    }
}

@Preview
@Composable
fun TodoItemCardPreview() {
    TodoItemCard(
        todo = TodoItem(
            id = 1,
            title = "Titlekjhkhu",
            timestamp = 1223445,
            description = "Description",
            completed = true,
            archived = false
        ),
        onDeleteClick = {},
        onCompleteClick = {},
        onArchiveClick = {},
        onCardClick = {}
    )
}
