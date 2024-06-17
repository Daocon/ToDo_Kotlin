package com.daocon.todo_kotlin.feature_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daocon.todo_kotlin.feature_todo.presentation.todo_list.TodoListScreen
import com.daocon.todo_kotlin.feature_todo.presentation.todo_list.TodoListViewModel
import com.daocon.todo_kotlin.feature_todo.presentation.util.Screen
import com.daocon.todo_kotlin.ui.theme.ToDo_KotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDo_KotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val listViewModel: TodoListViewModel = hiltViewModel()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodoItemListScreen.route,
                    ) {
                        composable(route = Screen.TodoItemListScreen.route) {
                            TodoListScreen(
                                navController = navController,
                                viewModel = listViewModel
                            )
                        }
                        composable(route = Screen.TodoNewUpdateScreen.route) {

                        }
                    }
                }
            }
        }
    }
}
