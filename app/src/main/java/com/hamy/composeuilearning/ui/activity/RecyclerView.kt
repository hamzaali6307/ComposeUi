package com.hamy.composeuilearning.ui.activity

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamy.composeuilearning.R
import com.hamy.composeuilearning.ui.model.Post
import com.hamy.composeuilearning.ui.network.viewModel.PostViewModel
import com.hamy.composeuilearning.ui.theme.ComposeUiLearningTheme
import com.hamy.composeuilearning.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerView : ComponentActivity() {
    private val viewModel: PostViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiLearningTheme() {
                Scaffold() {
                    GetApiData(viewModel)
                    //Recyclerview(data = dumyData())
                }
            }
        }
    }

    @Preview(showBackground = true, name = "light mode")
    @Preview(showBackground = true, name = "dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun EachRow(
        user: Post = Post(1, "testing"),
    ) {
        Card(
            modifier = Modifier.padding(8.dp, 8.dp),
            shape = RoundedCornerShape(CornerSize(10.dp)),
            elevation = 2.dp
        ) {
            Row(modifier = Modifier.padding(5.dp)) {
                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterVertically)
                        .clip(RoundedCornerShape(CornerSize(10.dp)))
                )
                Text(text = user.body, modifier = Modifier.padding(5.dp))
            }
        }
    }

    @Composable
    private fun GetApiData(viewModel: PostViewModel) {
        when (val result = viewModel.response.value) {
            is ApiState.Success -> {
                LazyColumn {
                    items(result.data) { it ->
                        EachRow(user = it)
                    }
                }
            }
            is ApiState.Loading -> {
                CircularProgressIndicator()
            }
            is ApiState.Failure -> {
                Text(text = "${result.message}")
            }
            is ApiState.Empty -> {
              //  CircularProgressIndicator()
            }
        }
    }
//    @Composable
//    private fun Recyclerview(data:List<Post>){
//        LazyColumn{
//            items(data){ resp ->
//                EachRow(user = resp)
//            }
//        }
//    }
}
