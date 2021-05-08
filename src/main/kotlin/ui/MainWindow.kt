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
import ui.components.ItemList
import ui.components.PostCard
import ui.components.TopBar

@Suppress("FunctionName")
class MainWindow(
    private val title: String,
    private val theme: Colors
) {
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
                        PostCard(it)
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
}