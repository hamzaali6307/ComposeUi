package com.hamy.composeuilearning.repository

import com.hamy.composeuilearning.ui.model.Todo
import com.hamy.composeuilearning.ui.network.dao.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dao: TodoDao) {

    suspend fun insert(todo: Todo) = withContext(Dispatchers.IO){
        dao.insert(todo)
    }

    fun getTodoList() : Flow<List<Todo>> = dao.getAllTodos()
}