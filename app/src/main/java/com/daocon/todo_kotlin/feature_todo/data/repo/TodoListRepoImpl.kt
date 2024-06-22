package com.daocon.todo_kotlin.feature_todo.data.repo

import android.util.Log
import com.daocon.todo_kotlin.feature_todo.data.di.IoDispatcher
import com.daocon.todo_kotlin.feature_todo.data.local.TodoDao
import com.daocon.todo_kotlin.feature_todo.data.mapper.toLocalTodoItem
import com.daocon.todo_kotlin.feature_todo.data.mapper.toLocalTodoItemListFromRemote
import com.daocon.todo_kotlin.feature_todo.data.mapper.toRemoteTodoItem
import com.daocon.todo_kotlin.feature_todo.data.mapper.toTodoItem
import com.daocon.todo_kotlin.feature_todo.data.mapper.toTodoItemListFromLocal
import com.daocon.todo_kotlin.feature_todo.data.remote.TodoApi
import com.daocon.todo_kotlin.feature_todo.domain.model.TodoItem
import com.daocon.todo_kotlin.feature_todo.domain.repo.TodoListRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

class TodoListRepoImpl(
    private val dao: TodoDao,
    private val api: TodoApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): TodoListRepo {
    override suspend fun getAllTodo(): List<TodoItem> {
        getAllTodosFromRemote()
        return dao.getAllTodoItems().toTodoItemListFromLocal()
    }

    override suspend fun getAllTodoFromLocalCache(): List<TodoItem> {
        return dao.getAllTodoItems().toTodoItemListFromLocal()
    }

    override suspend fun getAllTodosFromRemote() {
        return withContext(dispatcher) {
            try {
                refreshRoomCache()
            } catch (e: Exception) {
                when(e){
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("TodoListRepoImpl", "Error: ${e.message}")
                        if(isCacheEmpty()){
                            Log.e("Cache", "Error: No data from local Room cache")
                            throw Exception("Error: Device is offline and no data in local cache")
                        }
                    } else -> throw e
                }
            }
        }
    }

    private suspend fun refreshRoomCache(){
        val remoteBooks = api.getAllTodos().filterNotNull()
        dao.addAllTodoItem(remoteBooks.toLocalTodoItemListFromRemote())
    }

    private fun isCacheEmpty(): Boolean {
        var empty = true
        if (dao.getAllTodoItems().isNotEmpty()) empty = false
        return empty
    }

    override suspend fun getSingleTodoItemById(id: Int): TodoItem? {
        return dao.getSingleTodoItemById(id)?.toTodoItem()
    }

    override suspend fun addTodoItem(todo: TodoItem) {
        val newId = dao.addTodoItem(todo.toLocalTodoItem())
        val id = newId.toInt()
        val url = "todo/$id.json"
        api.addTodo(url, todo.toRemoteTodoItem().copy(id = id))

    }

    override suspend fun deleteTodoItem(todo: TodoItem) {
        try{
            val response = api.deleteTodo(todo.id)
            if(response.isSuccessful){
                Log.i("API_DELETE","Response Successful")
            }else{
                Log.i("API_DELETE","Response Unsuccessful")
                Log.i("API_DELETE",response.message())
            }
        }catch (e: Exception){
            when(e){
                is UnknownHostException, is ConnectException, is HttpException -> {
                    Log.e("HTTP", "Error: Could not delete")
                }else -> throw e
            }
        }
        dao.deleteTodoItem(todo.toLocalTodoItem())
    }

    override suspend fun updateTodoItem(todo: TodoItem) {
        dao.addTodoItem(todo.toLocalTodoItem())
        api.updateTodo(todo.id, todo.toRemoteTodoItem())
    }

}