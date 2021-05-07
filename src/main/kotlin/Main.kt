import data.Post
import datasource.DataSource
import datasource.DataSourceImpl
import repository.PostsRepository
import repository.PostsRepositoryImpl
import ui.MainWindow
import util.getTheme

fun main() {
    val dataSource: DataSource<Post> = DataSourceImpl()
    val repo: PostsRepository = PostsRepositoryImpl(dataSource)
    MainWindow("Bloggit", getTheme()).mainWindow(repo) {
        repo.close()
    }
}