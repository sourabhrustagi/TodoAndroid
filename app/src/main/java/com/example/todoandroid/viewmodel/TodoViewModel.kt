package com.example.todoandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoandroid.data.TodoRepository
import com.example.todoandroid.domain.usecase.AddTodoUseCase
import com.example.todoandroid.domain.usecase.DeleteTodoUseCase
import com.example.todoandroid.domain.usecase.EditTodoUseCase
import com.example.todoandroid.domain.usecase.GetTodoUseCase
import com.example.todoandroid.domain.usecase.ToggleTodoUseCase
import com.example.todoandroid.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodoUseCase: GetTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val editTodoUseCase: EditTodoUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {
    val todos: StateFlow<List<Todo>> = getTodoUseCase()

    fun addTodo(title: String) {
        viewModelScope.launch {
            addTodoUseCase(title)
        }
    }

    fun editTodo(id: Long, newTitle: String) {
        viewModelScope.launch {
            editTodoUseCase(id, newTitle)
        }
    }

    fun toggleTodo(id: Long) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
        }
    }

    fun deleteTodo(id: Long) {
        viewModelScope.launch {
            deleteTodoUseCase(id)
        }
    }
}