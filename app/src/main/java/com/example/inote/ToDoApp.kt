
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inote.AddTodoScreen
import com.example.inote.EditTodoScreen
import com.example.inote.TodoListScreen

data class TodoItem(
    val id: Int,
    var title: String,
    var subtitle: String,
    val check: MutableState<Boolean> = mutableStateOf(false)
)


@Composable
fun TodoApp() {
    val navController = rememberNavController()
    val todoList = remember { mutableStateListOf<TodoItem>() }

    NavHost(navController = navController, startDestination = "todoList") {
        composable("todoList") { TodoListScreen(navController, todoList) }
        composable("addTodo") { AddTodoScreen(navController, todoList) }
        composable("editTodo/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
            val todoItem = todoList.find { it.id == itemId }
            todoItem?.let { EditTodoScreen(navController, it) }
        }
    }
}

