package datasource

import data.Post
import kotlinx.coroutines.flow.flow

class DataSourceImpl(private val repo: String): DataSource<Post> {

    override fun geData() = flow {
        emit(Post("1", "Text1"))
        emit(Post("2", "Text2"))
        emit(Post("3", "Text3"))
    }

}