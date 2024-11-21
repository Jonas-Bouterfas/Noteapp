package com.example.inote

import TodoItem
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(navController: NavController, todoList: MutableList<TodoItem>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Todo List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addTodo") }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Todo")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(todoList) { item ->
                ListItem(headlineText = { /*TODO*/ })
                ListItem(
                    headlineText = {
                        Checkbox(
                            checked = item.check.value,
                            onCheckedChange = {
                                item.check.value = !item.check.value
                            })
                    },
                    overlineText = { Text(item.title) },
                    supportingText = { Text(item.subtitle) },
                    trailingContent = {
                        Row {
                            IconButton(
                                onClick = { navController.navigate("editTodo/${item.id}") }
                            ) {
                                Icon(Icons.Filled.Edit, contentDescription = "Edit Todo")
                            }
                            IconButton(
                                onClick = { todoList.remove(item) }
                            ) {
                                Icon(Icons.Filled.Delete, contentDescription = "Delete Todo")
                            }
                        }
                    }
                )
                Divider()

            }
        }
    }
}

