package oleksand.narvatov.giphyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemoteKeyDao
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.model.local.RemoteKey

@Database(
    entities = [
        RemoteKey::class,
        Giphy::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRemoteKeyDao() : RemoteKeyDao

    abstract fun getGiphyDao() : GiphyDao

}