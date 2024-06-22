package com.daocon.todo_kotlin.feature_todo.data.di

import android.content.Context
import androidx.room.Room
import com.daocon.todo_kotlin.feature_todo.data.local.TodoDao
import com.daocon.todo_kotlin.feature_todo.data.local.TodoDataBase
import com.daocon.todo_kotlin.feature_todo.data.remote.TodoApi
import com.daocon.todo_kotlin.feature_todo.data.repo.TodoListRepoImpl
import com.daocon.todo_kotlin.feature_todo.domain.repo.TodoListRepo
import com.daocon.todo_kotlin.feature_todo.presentation.todo_list.TodoListEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    fun providesRetrofitApi(retrofit: Retrofit): TodoApi {
        return retrofit.create(TodoApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://todo-6d490-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesRoomDao(database: TodoDataBase): TodoDao{
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDb(
        @ApplicationContext appContext: Context
    ): TodoDataBase{
        return Room.databaseBuilder(
            appContext.applicationContext,
            TodoDataBase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesTodoRepo(db: TodoDataBase, api: TodoApi, @IoDispatcher dispatcher: CoroutineDispatcher): TodoListRepo {
        return TodoListRepoImpl(db.dao, api, dispatcher)
    }
}