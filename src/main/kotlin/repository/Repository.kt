package repository

import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    val data: Flow<T>
}