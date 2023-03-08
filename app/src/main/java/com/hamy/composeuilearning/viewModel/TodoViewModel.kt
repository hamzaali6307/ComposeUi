package com.hamy.composeuilearning.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamy.composeuilearning.repository.TodoRepository
import com.hamy.composeuilearning.ui.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository):ViewModel() {

    var response :MutableState<List<Todo>> = mutableStateOf(listOf())

    init {
          getTodoList()
    }

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }

    private fun getTodoList() = viewModelScope.launch {
        repository.getTodoList()
            .catch { error ->
               Log.d("dataError",error.message.toString())
            }
            .collect{
                response.value = it
        }
    }
}