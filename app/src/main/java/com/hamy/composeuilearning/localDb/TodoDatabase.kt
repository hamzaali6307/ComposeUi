package com.hamy.composeuilearning.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamy.composeuilearning.ui.model.Todo
import com.hamy.composeuilearning.ui.network.dao.TodoDao

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {

    abstract fun getTodo() :TodoDao

}