package ismaelparedesmoreno.todolist.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ismaelparedesmoreno.todolist.model.TodoModel
import ismaelparedesmoreno.todolist.model.TodosModel
import ismaelparedesmoreno.todolist.preference.ModelPreferencesManager

class TodoViewModel : ViewModel() {
    private var todoModel = mutableStateListOf<TodoModel>()

    init {
        val todos = ModelPreferencesManager.get<TodosModel>("TODO")?.todos ?: mutableStateListOf()
        todoModel.addAll(todos)
    }

    fun getAllTodoModel(): List<TodoModel> {
        return todoModel
    }

    fun addTodoModel(todoItem: TodoModel) {
        todoModel.add(todoItem)
        val todos = TodosModel(todoModel)
        ModelPreferencesManager.put(todos, "TODO")
    }

    fun removeTodoItem(todoItem: TodoModel) {
        todoModel.remove(todoItem)
        val todos = TodosModel(todoModel)
        ModelPreferencesManager.put(todos, "TODO")
    }

    fun markAsComplete(todoItem: TodoModel, value: Boolean) {
        val index = todoModel.indexOf(todoItem)
        todoModel[index] = todoModel[index].let {
            it.copy(
                id = it.id,
                title = it.title,
                isCompleted = value
            )
        }
        val todos = TodosModel(todoModel)
        ModelPreferencesManager.put(todos, "TODO")
    }
}