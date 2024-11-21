package com.example.inote

import TodoItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(navController: NavController, todoList: MutableList<TodoItem>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Todos 🌟", style = androidx.compose.material3.MaterialTheme.typography.titleLarge) },
                colors = androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF673AB7), // Deep Purple
                    titleContentColor = androidx.compose.ui.graphics.Color.White
                )
            )
        },
        containerColor = androidx.compose.ui.graphics.Color(0xFFEDE7F6) // Soft Purple Background
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Header Section
            Text(
                text = if (todoList.isEmpty()) "No Tasks Yet! Tap Below to Add One 🎉" else "Your Tasks 📝",
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                color = androidx.compose.ui.graphics.Color(0xFF311B92), // Dark Purple
                modifier = Modifier
                    .padding(16.dp)
                    .align(androidx.compose.ui.Alignment.CenterHorizontally)
            )

            // Task List Section
            if (todoList.isNotEmpty()) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(todoList) { item ->
                        TodoListItem(navController = navController, todoItem = item, todoList = todoList)
                        Divider(color = androidx.compose.ui.graphics.Color(0xFFBDBDBD), thickness = 1.dp)
                    }
                }
            }

            // Add Button Section
            FloatingActionButton(
                onClick = { navController.navigate("addTodo") },
                containerColor = androidx.compose.ui.graphics.Color(0xFF4CAF50), // Green
                contentColor = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .align(androidx.compose.ui.Alignment.CenterHorizontally)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Todo")
            }
        }
    }
}

@Composable
fun TodoListItem(navController: NavController, todoItem: TodoItem, todoList: MutableList<TodoItem>) {
    androidx.compose.material3.Card(
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color.White
        ),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Checkbox(
                checked = todoItem.check.value,
                onCheckedChange = { todoItem.check.value = !todoItem.check.value },
                colors = androidx.compose.material3.CheckboxDefaults.colors(
                    checkedColor = androidx.compose.ui.graphics.Color(0xFF673AB7),
                    uncheckedColor = androidx.compose.ui.graphics.Color(0xFF757575)
                )
            )
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(
                    text = todoItem.title,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    color = androidx.compose.ui.graphics.Color(0xFF311B92) // Dark Purple
                )
                Text(
                    text = todoItem.subtitle,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    color = androidx.compose.ui.graphics.Color(0xFF757575) // Subtle gray
                )
            }
            Row {
                IconButton(onClick = { navController.navigate("editTodo/${todoItem.id}") }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = androidx.compose.ui.graphics.Color(0xFF4CAF50)) // Green
                }
                IconButton(onClick = { todoList.remove(todoItem) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = androidx.compose.ui.graphics.Color(0xFFF44336)) // Red
                }
            }
        }
    }
}



