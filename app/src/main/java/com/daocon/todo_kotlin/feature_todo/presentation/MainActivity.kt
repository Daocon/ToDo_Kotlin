package com.daocon.todo_kotlin.feature_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.daocon.todo_kotlin.feature_todo.presentation.todo_list.TodoListScreen
import com.daocon.todo_kotlin.feature_todo.presentation.todo_list.TodoListViewModel
import com.daocon.todo_kotlin.feature_todo.presentation.todo_new_update.TodoNewUpdateScreen
import com.daocon.todo_kotlin.feature_todo.presentation.util.Screen
import com.daocon.todo_kotlin.ui.theme.ToDo_KotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDo_KotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val listViewModel: TodoListViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodoItemListScreen.route
                    ) {
                        composable(route = Screen.TodoItemListScreen.route) {
                            TodoListScreen(
                                viewModel = listViewModel,
                                navController = navController
                            )
                        }

                        composable(route = Screen.TodoNewUpdateScreen.route + "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(
                                    name = "todoId",
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {

                            // Log the todoId
                            val todoId = it.arguments?.getInt("todoId")
                            if (todoId != null) {
                                println("TodoId Home Activity: $todoId")
                            }else{
                                println("TodoId: null")
                            }

                            TodoNewUpdateScreen(navController = navController)

                        }
                    }

                }
            }
        }
    }
}