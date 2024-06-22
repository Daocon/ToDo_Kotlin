package com.daocon.todo_kotlin.feature_todo.presentation.todo_list

import com.daocon.todo_kotlin.feature_todo.domain.model.TodoItem
import com.daocon.todo_kotlin.feature_todo.domain.util.TodoItemOrder

sealed class TodoListEvent {
    data class Sort(val todoItemOrder: TodoItemOrder): TodoListEvent()
    data class Delete(val todo: TodoItem): TodoListEvent()
    data class ToggleCompleted(val todo: TodoItem): TodoListEvent()
    data class ToggleArchived(val todo: TodoItem): TodoListEvent()
    object UndoDelete: TodoListEvent()
}