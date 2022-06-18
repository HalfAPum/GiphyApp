package oleksand.narvatov.giphyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import oleksand.narvatov.giphyapp.data.local.dao.base.BaseDao
import oleksand.narvatov.giphyapp.data.local.dao.base.InsertDao
import oleksand.narvatov.giphyapp.model.local.RemovedGiphy

@Dao
interface RemovedGiphyDao : InsertDao<RemovedGiphy> {

    @Query("SELECT * FROM RemovedGiphy")
    suspend fun getAll() : List<RemovedGiphy>
}