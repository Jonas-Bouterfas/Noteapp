package com.example.inote

import TodoItem
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
                title = { Text("Add Todo", style = androidx.compose.material3.MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            validateAndAddTodo(title, subtitle, todoList, context, navController)
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF6200EA), // Purple
                    titleContentColor = androidx.compose.ui.graphics.Color.White
                )
            )
        },
        containerColor = androidx.compose.ui.graphics.Color(0xFFF3F4F6) // Light Gray Background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Create a new Todo",
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                color = androidx.compose.ui.graphics.Color(0xFF6200EA)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Styled TextField for Title
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Todo Title") },
                colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
                    containerColor = androidx.compose.ui.graphics.Color.White,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color(0xFF6200EA),
                    cursorColor = androidx.compose.ui.graphics.Color(0xFF6200EA)
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Styled TextField for Subtitle
            TextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text("Details") },
                colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
                    containerColor = androidx.compose.ui.graphics.Color.White,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color(0xFF6200EA),
                    cursorColor = androidx.compose.ui.graphics.Color(0xFF6200EA)
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Styled Button
            Button(
                onClick = {
                    validateAndAddTodo(title, subtitle, todoList, context, navController)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Add Todo",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}

// Validation logic extracted into a reusable function
private fun validateAndAddTodo(
    title: String,
    subtitle: String,
    todoList: MutableList<TodoItem>,
    context: Context,
    navController: NavController
) {
    when {
        title.isBlank() -> {
            Toast.makeText(context, "Title cannot be blank.", Toast.LENGTH_SHORT).show()
        }
        title.length < 3 || title.length > 50 -> {
            Toast.makeText(context, "Title must be between 3 and 50 characters.", Toast.LENGTH_SHORT).show()
        }
        subtitle.isBlank() -> {
            Toast.makeText(context, "Subtitle cannot be blank.", Toast.LENGTH_SHORT).show()
        }
        subtitle.length > 120 -> {
            Toast.makeText(context, "Subtitle must not exceed 120 characters.", Toast.LENGTH_SHORT).show()
        }
        else -> {
            todoList.add(TodoItem(id = todoList.size, title = title, subtitle = subtitle))
            navController.popBackStack()
        }
    }
}

