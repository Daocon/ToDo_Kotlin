package com.daocon.todo_kotlin.feature_todo.domain.util

sealed class SortingDirection {
    object Up: SortingDirection()
    object Down: SortingDirection()
}