package com.hamy.composeuilearning.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.hamy.composeuilearning.R
import com.hamy.composeuilearning.ui.model.Post
import com.hamy.composeuilearning.ui.model.Todo
import com.hamy.composeuilearning.ui.theme.ComposeUiLearningTheme
import com.hamy.composeuilearning.utils.myLogs
import com.hamy.composeuilearning.viewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity : ComponentActivity() {
    private val viewModel: TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiLearningTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    AddToolBar()
                }
            }
        }
    }

    @Preview(showBackground = true, name = "light mode")
    @Preview(showBackground = true, name = "dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun EachRow(
        user: Todo = Todo("hello", "testing"),
    ) {
        Card(
            modifier = Modifier.padding(8.dp, 8.dp),
            shape = RoundedCornerShape(CornerSize(10.dp)),
            elevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = user.title, fontWeight = FontWeight.Bold)
                Text(text = user.description, modifier = Modifier.padding(5.dp))
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
                val openDialog = remember { mutableStateOf(false) }
                FloatingActionButton(onClick = {
                    openDialog.value = true
                }) {
                    Icon(Icons.Default.Add, contentDescription = "add")
                    ShowAddTodoTask(openDialog = openDialog)
                }
            }
        ) {
            myLogs(it.toString()) // if not used gives warrning
            Recyclerview(data = viewModel)
        }
    }

    @Composable
    fun ShowAddTodoTask(openDialog: MutableState<Boolean>) {
        val title = remember {
            mutableStateOf("")
        }
        val description = remember {
            mutableStateOf("")
        }
        if (openDialog.value) {
            AlertDialog(onDismissRequest = {
                openDialog.value = false
            },
                title = {
                    Text(text = "Insert Task")
                },
                text = {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = title.value, onValueChange = {
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
                        OutlinedTextField(
                            value = description.value, onValueChange = {
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
                    OutlinedButton(onClick = {
                        insertTodo(title, description)
                        openDialog.value = false
                    }) {
                        Text(text = "Add")
                    }
                }
            )
        }
    }

    @Composable
    private fun Recyclerview(data: TodoViewModel) {
        LazyColumn {
            items(data.response.value) { resp ->
                EachRow(user = resp)
            }
        }
    }

    private fun insertTodo(title: MutableState<String>, description: MutableState<String>) {
        lifecycleScope.launchWhenStarted {
            if (!TextUtils.isEmpty(title.value) && !TextUtils.isEmpty(description.value)) {
                viewModel.insert(Todo(title.value, description.value))
                Toast.makeText(this@TodoActivity, "Inserted :)", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@TodoActivity, "Field never be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}