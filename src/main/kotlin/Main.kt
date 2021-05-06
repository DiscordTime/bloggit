import datasource.DataSourceImpl
import repository.PostsRepositoryImpl
import ui.MainWindow
import util.getTheme

fun main() {
    val dataSource = DataSourceImpl("")
    val postsRepository = PostsRepositoryImpl(dataSource)
    MainWindow("Bloggit", getTheme(), postsRepository).mainWindow()
}