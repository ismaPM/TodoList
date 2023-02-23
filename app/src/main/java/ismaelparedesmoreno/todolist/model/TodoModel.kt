package ismaelparedesmoreno.todolist.model

import java.util.*

data class TodosModel(
    var todos: List<TodoModel>
)

data class TodoModel(
    val id:String = UUID.randomUUID().toString(),
    val title:String,
    var isCompleted:Boolean = false
)