package com.hamy.composeuilearning.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamy.composeuilearning.ui.theme.ComposeUiLearningTheme
import com.hamy.composeuilearning.utils.myToast

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiLearningTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    LoginScreen()
                }
            }
        }
    }

    fun login(userName: String, password: String) {
        when {
            (userName.isNotEmpty() && password.isNotEmpty()) && userName == "hamza" && password == "12345" -> {
                myToast("login successfully")
            }
            else -> {
                myToast("user name/ password not matched")
            }
        }
    }

    @Preview(showBackground = true, name = "light mode")
    @Preview(showBackground = true, name = "dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun LoginScreen() {
        val userName = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Hello Again!",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
            )
            Text(
                text = "Welcome",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
            )
            Text(
                text = "back",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
            )

            OutlinedTextField(value = userName.value, onValueChange = {
                userName.value = it
            },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "person")
                },
                label = {
                    Text(text = "User Name")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(value = password.value, onValueChange = {
                password.value = it
            },
                leadingIcon = {
                    Icon(Icons.Default.Info, contentDescription = "password")
                },
                label = {
                    Text(text = "Password")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedButton(onClick = {
                login(userName.value,password.value)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Login")

            }
        }
    }
}