package com.example.codelab_day_02

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.codelab_day_02.ui.theme.Codelab_day_02Theme
import com.example.codelab_day_02.ui.theme.Teal200
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Codelab_day_02Theme {
//                 A surface container using the 'background' color from the theme
//                Surface(
//                    color = MaterialTheme.colors.background
//                ) {
//                    PhotographerCard(modifier = Modifier.fillMaxWidth(1f)) {
//                        Column() {
//                            Text("slot1")
//                            Text("slot2")
//                            Text("slot3")
//                            Text("slot4")
//                        }
//                    }
//                }
                LayoutsCodelab()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LayoutsCodelab() {

    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val lazyListscrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutCodelab")
                },
                actions = {
                    IconButton(onClick = {
                        Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }

                    IconButton(onClick = {
                        Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = null)
                    }
                }
            )
        },
        drawerContent = {
            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }

            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 홈 클릭")
            }) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }
        },
        floatingActionButton = {
            IconButton(onClick = {
                coroutineScope.launch {
//                    scrollState.scrollTo(0)
//                    scrollState.animateScrollTo(0)
                    lazyListscrollState.animateScrollToItem(0)
                }
            }) {
                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
            }
        }
    ) {
//        SomeText()
//            SimpleList(scrollState)
        LazySimpleList(lazyListscrollState)
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Image(
            painter = rememberImagePainter(
            data = "https://developer.android.com/images/brand/Android_Robot.png"),
        contentDescription = "Android Logo",
        modifier = Modifier.size(50.dp)
        )

        Text(text = "Item $index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun SimpleList(scrollState: ScrollState) {
    val getBGColor : (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }
    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text(text = "Item $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getBGColor(it)))
        }
    }
}

@Composable
fun LazySimpleList(scrollState: LazyListState) {
    val getBGColor : (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }
    LazyColumn(state = scrollState) {
        items(100) {
//            Text(text = "Item $it",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(getBGColor(it)))
            ImageListItem(it)
        }
    }
}

@Composable
fun SomeText() {
    Column() {
        Text(text = "Hi there")
        Text(text = "Thanks for going")
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier, contentSlot : @Composable () -> Unit) {

}

@Composable
fun PhotographerCard(modifier: Modifier, contentSlot: @Composable () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .background(Teal200)
        .padding(10.dp)) {
        Surface(modifier = Modifier.size(80.dp),
        shape = CircleShape,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)) {
        }

        Surface(
            modifier = Modifier
                .background(Color.Yellow)
                .weight(0.1f),
        content = contentSlot)

//        Column(verticalArrangement = Arrangement.spacedBy(3.dp),
//        modifier = Modifier
//            .background(Color.Yellow)
//            .weight(0.1f)) {
//            Text(text = "Alfred", fontWeight = FontWeight.Bold)
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text(text = "3 minutes ago", style = typography.body2)
//            }
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text(text = "이용불가", style = typography.caption)
//            }
//        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Codelab_day_02Theme {
//        PhotographerCard()
    }
}