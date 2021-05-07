package ui

import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import data.Post
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import repository.PostsRepository
import repository.Repository

@Suppress("FunctionName")
class MainWindow(
    private val title: String,
    private val theme: Colors
) {

    @Composable
    fun PostItem(post: Post) {
        val id = post.id
        val text = post.text
        Card(
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp).size(400.dp, 200.dp),
            shape = MaterialTheme.shapes.large,
            backgroundColor = theme.surface,
            elevation = 8.dp,
        ) {
            Column(modifier = Modifier.padding(0.dp, 10.dp).fillMaxWidth()) {
                Text("id: $id", color = theme.primary, modifier = Modifier.align(Alignment.CenterHorizontally))
                Text("content: $text", color = theme.primary, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }

    @Composable
    fun <T> ItemList(
        itemList: List<T>,
        modifier: Modifier = Modifier.fillMaxSize().background(theme.background),
        horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
        dividerThickness: Dp = 2.dp,
        dividerColor: Color = theme.secondary,
        content: @Composable (T) -> Unit
    ) {
        val itemListState = remember { mutableStateListOf(itemList) }
        Box(
            modifier = modifier,
        ) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                horizontalAlignment = horizontalAlignment
            ) {
                items(itemListState) { list ->
                    list.forEach { item ->
                        Divider(Modifier.fillMaxWidth(), thickness = dividerThickness, color = dividerColor)
                        content(item)
                    }
                }
            }
        }
    }

    @Composable
    fun <T> Content(
        repo: Repository<T>,
        content: @Composable (List<T>) -> Unit
    ) {
        val list = remember { mutableStateListOf<T>() }
        LaunchedEffect(Unit) {
            repo.data.onEach {
                list.add(it)
            }.launchIn(this)
        }
        content(list)
    }

    @Composable
    fun TopBar(title: String) {
        TopAppBar(
            title = { Text(title) },
            actions = {
                Button(onClick = {
                    println("Settings clicked!")
                }) {
                    Text("Settings")
                }
            }
        )
    }

    fun mainWindow(
        postsRepo: PostsRepository,
        onDismissRequest: () -> Unit
    ) = Window (
        title = title,
        onDismissRequest = { onDismissRequest() }
    ) {
        MaterialTheme(theme) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopBar(title)
                Content(postsRepo) { posts ->
                    ItemList(posts) {
                        PostItem(it)
                    }
                }
            }
        }
    }
}