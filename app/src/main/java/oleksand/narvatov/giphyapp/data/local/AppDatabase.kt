package oleksand.narvatov.giphyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemoteKeyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemovedGiphyDao
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.model.local.RemoteKey
import oleksand.narvatov.giphyapp.model.local.RemovedGiphy

@Database(
    entities = [
        RemoteKey::class,
        Giphy::class,
        RemovedGiphy::class,
    ],
    version = 4,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase(), TransactionManager {

    abstract fun getRemoteKeyDao() : RemoteKeyDao

    abstract fun getGiphyDao() : GiphyDao

    abstract fun getRemovedGiphyDao() : RemovedGiphyDao

    override suspend fun runInTransaction(action: suspend () -> Unit) {
        withTransaction {
            action.invoke()
        }
    }

}