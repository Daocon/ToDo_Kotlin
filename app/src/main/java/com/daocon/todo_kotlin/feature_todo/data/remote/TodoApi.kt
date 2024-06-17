package com.daocon.todo_kotlin.feature_todo.data.remote

import com.daocon.todo_kotlin.feature_todo.data.remote.dto.RemoteTodoItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface TodoApi {
    @GET("todo.json")
    suspend fun getAllTodos(): List<RemoteTodoItem>

    @GET("todo.json?orderBy=\"id\"")
    suspend fun getSingleTodoById(@Query("equalTo") id: Int?): Map<String, RemoteTodoItem>

//    @POST("todo.json")
//    suspend fun addTodo(@Body url: String, @Body updatedTodo: RemoteTodoItem): Response<Unit>

    @PUT
    suspend fun addTodo(@Url url: String, @Body updatedTodo: RemoteTodoItem): Response<Unit>

    @DELETE("todo/{id}.json")
    suspend fun deleteTodo(@Path("id") id: Int?): Response<Unit>

    @PUT("todo/{id}.json")
    suspend fun updateTodo(
        @Path("id") id: Int?,
        @Body updatedTodo: RemoteTodoItem
    ): Response<Unit>


}