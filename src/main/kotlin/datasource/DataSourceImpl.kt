package datasource

import data.Post
import kotlinx.coroutines.flow.flow

class DataSourceImpl(private val repo: String): DataSource<Post> {

    override fun geData() = flow {
        emit(Post("1", "Text1"))
        kotlinx.coroutines.delay(1000)
        emit(Post("2", "Text2"))
        kotlinx.coroutines.delay(2000)
        emit(Post("3", "Text3"))
        kotlinx.coroutines.delay(1000)
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
        emit(Post("4", "Text4"))
    }

}