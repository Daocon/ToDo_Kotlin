package com.daocon.todo_kotlin.feature_todo.domain.model

import com.google.gson.annotations.SerializedName

data class TodoItem(
    val title: String,
    val description: String,
    val timestamp: Long,
    val completed: Boolean,
    val archived: Boolean,
    val id: Int?
)
