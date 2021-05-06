package datasource

import kotlinx.coroutines.flow.Flow

interface DataSource<T> {

    fun geData() : Flow<T>

}