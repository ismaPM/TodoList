package ismaelparedesmoreno.todolist.views.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ismaelparedesmoreno.todolist.R
import ismaelparedesmoreno.todolist.viewmodel.TodoViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {

    val openDialog = remember { mutableStateOf(false) }
    val todoViewModel by remember { mutableStateOf(TodoViewModel()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true;
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "",
                    )
                if (openDialog.value) {
                    AddScreenDialog(
                        openDialog = openDialog,
                        todoViewModel = todoViewModel
                    )
                }
            }
        },
        topBar = {
            TopAppBar() {
                Text(stringResource(R.string.todoList))
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TodoListScreen(todoViewModel)
        }
    }
}