package com.daocon.todo_kotlin.feature_todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daocon.todo_kotlin.feature_todo.data.local.dto.LocalTodoItem


@Database(
    entities = [LocalTodoItem::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDataBase: RoomDatabase() {
    abstract val dao: TodoDao

    companion object {
        const val DATABASE_NAME = "todo_db"
    }
}