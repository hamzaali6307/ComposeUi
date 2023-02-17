package com.hamy.composeuilearning.ui.network.repository

import com.hamy.composeuilearning.ui.model.Post
import com.hamy.composeuilearning.ui.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiService) {

    fun getPost() = flow<List<Post>> {
        emit(apiService.getPost())

    }.flowOn(Dispatchers.IO)
}