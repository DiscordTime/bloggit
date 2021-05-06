package repository

import data.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

class PostsRepositoryImpl: PostsRepository<Post> {
    override fun getPosts(): Flow<Post> {
        TODO("Not yet implemented")
    }

}