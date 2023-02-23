package ismaelparedesmoreno.todolist.views.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import ismaelparedesmoreno.todolist.R
import ismaelparedesmoreno.todolist.viewmodel.TodoViewModel
import ismaelparedesmoreno.todolist.model.TodoModel

@Composable
fun AddScreenDialog(
    openDialog: MutableState<Boolean>,
    todoViewModel: TodoViewModel = viewModel()
) {

    var item by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(
                text = stringResource(R.string.nuevo), modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        },

        text = {
            Column {
                TextField(
                    value = item, onValueChange = { newText ->
                        item = newText
                    }, modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (item.text.isNotEmpty()) {
                        todoViewModel.addTodoModel(
                            TodoModel(
                                title = item.text
                            )
                        )
                        openDialog.value = false
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            ) {
                Text(
                    stringResource(R.string.agregar), color = Color.White,
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    openDialog.value = false
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            ) {
                Text(
                    stringResource(R.string.cancelar), color = Color.White,
                )
            }
        }
    )


}