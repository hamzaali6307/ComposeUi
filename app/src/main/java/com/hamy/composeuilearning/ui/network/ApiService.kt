package com.hamy.composeuilearning.ui.network

import com.hamy.composeuilearning.ui.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost(): List<Post>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}