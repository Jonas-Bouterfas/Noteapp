package com.example.inote

import TodoItem
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(navController: NavController, todoList: MutableList<TodoItem>) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Todo") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (title.isNotBlank() && subtitle.isNotBlank()) {
                                if(title.length >= 5) {
                                    todoList.add(
                                        TodoItem(
                                            id = todoList.size,
                                            title = title,
                                            subtitle = subtitle
                                        )
                                    )
                                    navController.popBackStack()
                                }
                                else{
                                    println("Du har för få bokstäver")
                                }
                            }
                        }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Todo") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text("Details") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (title.isNotBlank() && subtitle.isNotBlank()) {
                    if (subtitle.length >= 4) {
                        todoList.add(
                            TodoItem(
                                id = todoList.size,
                                title = title,
                                subtitle = subtitle
                            )
                        )
                        navController.popBackStack()
                    }else{
                        println("haha")
                        Toast.makeText(
                            context,
                            "Subtitle must be at least 4 characters long.", // Ensure this is a valid string
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

            }) {
                Text("Add Todo")
            }
        }
    }
}
