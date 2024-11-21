package com.example.inote

import TodoItem
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTodoScreen(navController: NavController, todoItem: TodoItem) {
    var title by remember { mutableStateOf(todoItem.title) }
    var subtitle by remember { mutableStateOf(todoItem.subtitle) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Task 🛠️", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF512DA8), // Deep Purple
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFEDE7F6) // Light Purple Background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Update your task details below:",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF311B92) // Dark Purple
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Title Input Field
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Task Title") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF512DA8),
                    cursorColor = Color(0xFF512DA8)
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle Input Field
            TextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text("Task Details") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF512DA8),
                    cursorColor = Color(0xFF512DA8)
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Save Button
            Button(
                onClick = {
                    if (title.length < 3 || title.length > 50 ) {
                        Toast.makeText(context, "Title must be at least 3 characters long and not more thAN FIFTY CHARACTER", Toast.LENGTH_SHORT).show()
                    } else if (subtitle.isBlank()) {
                        Toast.makeText(context, "Task details cannot be empty.", Toast.LENGTH_SHORT).show()
                    } else if (subtitle.length > 120) {
                        Toast.makeText(context, "Task details must be under 120 characters.", Toast.LENGTH_SHORT).show()
                    } else {
                        todoItem.title = title.trim()
                        todoItem.subtitle = subtitle.trim()
                        Toast.makeText(context, "Task updated successfully!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF512DA8), // Deep Purple
                    contentColor = Color.White
                )
            ) {
                Text("Save Changes", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

