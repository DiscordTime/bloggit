package datasource

import data.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.revwalk.RevWalk
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File

class DataSourceImpl(private val path: String = ".") : DataSource<Post> {

    private val repo: /* Git */ Repository = openRepo()

    override val data = repo.commits().asPosts()

    private fun openRepo(): Repository =
        FileRepositoryBuilder()
            .setGitDir(File("$path/.git"))
            .build()

    private fun Repository.commits(start: String = "HEAD"): Flow<RevCommit> =
        flow {
            RevWalk(this@commits).use {
                println("RevWalk $it started")
                it.markStart(it.parseCommit(this@commits.resolve(start)))
                it.forEach { commit ->
                    delay(1000)
                    println("Emitting commit ${commit.id.name}")
                    emit(commit)
                }
            }
            println("RevWalk closed")
        }.flowOn(Dispatchers.IO)

    // TODO: does this belong here?
    private fun Flow<RevCommit>.asPosts(): Flow<Post> =
        map { commit -> Post(commit.id.name, commit.fullMessage) }

    override fun close() {
        repo.close()
        println("DataSource $this closed")
    }

}