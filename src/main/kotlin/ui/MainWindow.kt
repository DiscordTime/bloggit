package ui

import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.Post
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import repository.PostsRepository

class MainWindow(private val title: String, private val theme: Colors, postsRepository: PostsRepository<Post>) {

    private val posts = postsRepository.getPosts()

    @Composable
    fun postItem(post: Post) {
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
    fun listOfPosts(posts: List<Post>) {
        val postList = remember { mutableStateListOf(posts) }
        Box(
            modifier = Modifier.fillMaxSize().background(theme.background),
        ) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(postList) { list ->
                    list.forEach { post ->
                        Divider(Modifier.fillMaxWidth(), thickness = 2.dp, color = theme.secondary)
                        postItem(post)
                    }
                }
            }
        }
    }

    @Composable
    fun content() {
        val postList = remember { mutableStateListOf<Post>() }
        LaunchedEffect(Unit) {
            posts.onEach {
                postList.add(it)
            }.launchIn(this)
        }
        listOfPosts(postList)
    }

    @Composable
    fun topBar(title: String) {
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

    fun mainWindow() = Window (title = title) {
        MaterialTheme(theme) {
            Column(modifier = Modifier.fillMaxSize()) {
                topBar(title)
                content()
            }
        }
    }
}