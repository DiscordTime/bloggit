package repository

import kotlinx.coroutines.flow.Flow

interface PostsRepository<T> {

    fun getPosts(): Flow<T>
}