package com.example.todoandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoandroid.data.TodoRepository
import com.example.todoandroid.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {
    val todos: StateFlow<List<Todo>> = repository.todos

    fun addTodo(title: String) = repository.add(title)
    fun toggleTodo(id: Long) = repository.toggle(id)
    fun deleteTodo(id: Long) = repository.delete(id)
}