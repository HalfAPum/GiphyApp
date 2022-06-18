package oleksand.narvatov.giphyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import oleksand.narvatov.giphyapp.data.local.dao.base.BaseDao
import oleksand.narvatov.giphyapp.model.local.RemoteKey

@Dao
interface RemoteKeyDao : BaseDao<RemoteKey> {

    @Query("SELECT * FROM RemoteKey WHERE id = :id")
    suspend fun get(id: String): RemoteKey

    @Query("DELETE FROM RemoteKey")
    @JvmSuppressWildcards
    override suspend fun clear()

}