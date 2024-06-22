package com.daocon.todo_kotlin.feature_todo.domain.repo

import com.daocon.todo_kotlin.feature_todo.domain.model.TodoItem
interface TodoListRepo {
    suspend fun getAllTodo(): List<TodoItem>
    suspend fun getAllTodoFromLocalCache(): List<TodoItem>
    suspend fun getAllTodosFromRemote()
    suspend fun getSingleTodoItemById(id: Int): TodoItem?
    suspend fun addTodoItem(todo: TodoItem)
    suspend fun deleteTodoItem(todo: TodoItem)
    suspend fun updateTodoItem(todo: TodoItem)
}