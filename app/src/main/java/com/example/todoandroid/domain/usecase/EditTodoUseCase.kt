package com.example.todoandroid.domain.usecase

import com.example.todoandroid.data.TodoRepository
import javax.inject.Inject

class EditTodoUseCase @Inject constructor(
    private val repository: TodoRepository
){
    operator fun invoke(id: Long, newTitle: String, category: String? = null){
        repository.edit(id, newTitle, category)
    }
}