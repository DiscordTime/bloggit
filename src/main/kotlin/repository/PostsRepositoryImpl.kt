package repository

import data.Post
import datasource.DataSource

class PostsRepositoryImpl(
    private val dataSource: DataSource<Post>
) : PostsRepository {

    override val data get() = dataSource.data

    override fun close() {
        dataSource.close()
        println("PostsRepositoryImpl $this closed")
    }
}