package repository

import data.Post
import datasource.DataSource

class PostsRepositoryImpl(private val dataSource: DataSource<Post>): PostsRepository<Post> {

    override fun getPosts() = dataSource.geData()

}