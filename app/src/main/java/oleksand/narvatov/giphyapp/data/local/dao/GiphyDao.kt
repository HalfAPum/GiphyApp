package oleksand.narvatov.giphyapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import oleksand.narvatov.giphyapp.data.local.dao.base.BaseDao
import oleksand.narvatov.giphyapp.model.local.Giphy

@Dao
interface GiphyDao : BaseDao<Giphy> {

    @Query("SELECT * FROM Giphy")
    fun getPagingSource(): PagingSource<Int, Giphy>

    @Query("DELETE FROM Giphy")
    @JvmSuppressWildcards
    override suspend fun clear()

}