import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.hamy.composeuilearning.ui.model.Pager
import com.hamy.composeuilearning.ui.model.listDataPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen() {
    val pageState = rememberPagerState()

    Column {
        HorizontalPager(
            count = listDataPager.size,
            state = pageState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { pager ->
            PagerUi(pager = listDataPager[pager])
        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            activeColor = colorResource(id = com.hamy.composeuilearning.R.color.purple_200)
        )
        AnimatedVisibility(visible = pageState.currentPage == 2 , modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button( onClick = { /*TODO*/ }, modifier = Modifier.width(200.dp)
                .padding(10.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =  colorResource(id = com.hamy.composeuilearning.R.color.purple_200),
                    contentColor = Color.White)
            ) {
                Text(text = "Get Started...")
            }
        }
    }
}

@Composable
fun PagerUi(pager: Pager) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = pager.image), contentDescription = pager.description,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = pager.description)
    }
}