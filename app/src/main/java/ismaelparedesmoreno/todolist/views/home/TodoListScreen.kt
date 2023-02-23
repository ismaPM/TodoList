package ismaelparedesmoreno.todolist.views.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ismaelparedesmoreno.todolist.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(todoViewModel: TodoViewModel = viewModel()) {
    if (todoViewModel.getAllTodoModel().isEmpty())
        Text(
            text = "", textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxHeight()
        )
    else {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(todoViewModel.getAllTodoModel()) {
                Card(
                    elevation = 1.dp,
                    modifier = Modifier.padding(5.dp).fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                    ) {
                        Text(
                            it.title,
                            textDecoration =
                            if (it.isCompleted) {
                                TextDecoration.LineThrough
                            } else {
                                TextDecoration.None
                            }, modifier = Modifier.width(200.dp)
                        )
                        Spacer(modifier = Modifier.fillMaxSize(0.5f))

                        Checkbox(
                            checked = it.isCompleted,
                            onCheckedChange = { value ->
                                todoViewModel.markAsComplete(
                                    todoItem = it,
                                    value = value,
                                )
                            },
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Filled.Delete, contentDescription = "Delete",
                            modifier = Modifier.clickable {
                                todoViewModel.removeTodoItem(it)
                            },
                            tint = Color.Red
                        )
                    }
                }
            }//finish items
        }//finish LazyColumn
    }
}