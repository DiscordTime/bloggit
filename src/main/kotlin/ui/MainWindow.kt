package ui

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import data.Post
import datasource.DataSourceImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import repository.PostsRepositoryImpl

class MainWindow {

    private val posts = PostsRepositoryImpl(DataSourceImpl("")).getPosts()

    @Composable
    fun buttonText() {
        MaterialTheme {
            Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        println("Button clicked")
                        runBlocking {
                            posts.collect {
                                println("post $it")
                            }
                        }
                    }) {
                    Text("Click here!")
                }
            }
        }
    }

    fun mainWindow() = Window (title = "Compose for Desktop", size = IntSize(300, 300)) {
        buttonText()
    }
}