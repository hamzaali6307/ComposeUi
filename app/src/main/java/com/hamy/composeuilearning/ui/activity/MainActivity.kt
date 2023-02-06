package com.hamy.composeuilearning.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hamy.composeuilearning.ui.theme.ComposeUiLearningTheme
import com.hamy.composeuilearning.utils.myLogs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiLearningTheme {
                Scaffold(
//                    topBar = {
//                        TopAppBar(
//                            title = {
//                                Text(text = "Demo")
//                            },
//                            navigationIcon = {
//                                IconButton(onClick = { myToast("click menu") }) {
//                                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
//                                }
//                            },
//                            actions = {
//                                IconButton(onClick = { myToast("click Notification") }) {
//                                    Icon(Icons.Filled.Notifications,
//                                        contentDescription = "Notification")
//                                }
//                                IconButton(onClick = { myToast("click Search") }) {
//                                    Icon(Icons.Filled.Search, contentDescription = "Search")
//                                }
//                            }
//                        )
//                    },
//                    floatingActionButton = {
//                        FloatingActionButton(onClick = { myToast("click from floating action button") }) {
//                            IconButton(onClick = { myToast("click floating") }) {
//                                Icon(Icons.Filled.Add, contentDescription = "floating")
//                            }
//                        }
//                    },
//
//                    floatingActionButtonPosition = FabPosition.End
                ) { padding ->
                    myLogs(padding.toString())
                    Greeting("hamza")
                }
            }
        }
    }

   /* @Preview(showBackground = true, name = "light mode")
    @Composable
    fun ShowSwitch() {
        val isChecked = remember {
            mutableStateOf(true)
        }
        Switch(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it

            })
    }*/

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!", color = MaterialTheme.colors.error)
    }

    @Preview(showBackground = true, name = "light mode")
    @Preview(showBackground = true, name = "dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun DefaultPreview() {
        ComposeUiLearningTheme {
            Greeting("Android")
        }
    }
}
