package com.hamy.composeuilearning.utils

import com.hamy.composeuilearning.ui.model.Post

sealed class ApiState{

    class Success(val data: List<Post>) : ApiState()
    class Failure(val message: Throwable) : ApiState()
    object Empty : ApiState()
    object Loading : ApiState()

}

