package com.hamy.composeuilearning.ui.network.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamy.composeuilearning.ui.model.Post
import com.hamy.composeuilearning.ui.network.repository.PostRepository
import com.hamy.composeuilearning.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    val response: MutableState<ApiState> = mutableStateOf(ApiState.Loading)

    init {
        getPost()
    }

    private fun getPost() = viewModelScope.launch {
        postRepository.getPost().onStart {
            response.value = ApiState.Loading
        }.catch {
            response.value = ApiState.Failure(it)
        }.collect {
            response.value = ApiState.Success(it)
        }
    }
}