package com.hamy.composeuilearning.ui.activity

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hamy.composeuilearning.ui.theme.ComposeUiLearningTheme
import com.hamy.composeuilearning.utils.myLogs
import com.hamy.composeuilearning.utils.myToast
import kotlin.math.log

class TodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiLearningTheme() {
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }

    @Composable
    fun AddToolBar() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Todo App")
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { myLogs("mylog") }) {
                    Icon(Icons.Default.Add, contentDescription = "add")
                }
            }
        ) {
            myLogs(it.toString())
        }
    }

    @Composable
    fun ShowAddTodoTask() {
        val title = remember {
            mutableStateOf("")
        }
        val description = remember {
            mutableStateOf("")
        }
        AlertDialog(onDismissRequest = { /*TODO*/ },
            title = {
                Text(text = "Insert Task")
            },
            text = {
                Column(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()) {
                    OutlinedTextField(value = title.value, onValueChange = {
                        title.value = it
                    },
                        placeholder = {
                            Text(text = "Enter Title")
                        },
                        label = {
                            Text(text = "Enter Title")
                        },
                        modifier = Modifier.padding(10.dp)
                        
                    )
                    OutlinedTextField(value = description.value, onValueChange = {
                        description.value = it
                    },
                        placeholder = {
                            Text(text = "Enter Description")
                        },
                        label = {
                            Text(text = "Enter Description")
                        },
                        modifier = Modifier.padding(10.dp)
                    )
                }
            },
            confirmButton = {
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Add")
                }
            }
        )
    }

}