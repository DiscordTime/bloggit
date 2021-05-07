package datasource

import kotlinx.coroutines.flow.Flow

interface DataSource<T> : AutoCloseable {

    val data : Flow<T>
}