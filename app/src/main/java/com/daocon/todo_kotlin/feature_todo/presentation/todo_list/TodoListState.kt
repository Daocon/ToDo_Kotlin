package com.daocon.todo_kotlin.feature_todo.presentation.todo_list

import com.daocon.todo_kotlin.feature_todo.domain.model.TodoItem
import com.daocon.todo_kotlin.feature_todo.domain.util.SortingDirection
import com.daocon.todo_kotlin.feature_todo.domain.util.TodoItemOrder

data class TodoListState(
    val todoItems: List<TodoItem> = emptyList(),
    val todoItemOrder: TodoItemOrder,
    val isLoading: Boolean = true,
    val error: String? = null
)
