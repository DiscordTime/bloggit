package ui.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import java.sql.DriverManager.println

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