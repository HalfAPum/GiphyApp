package oleksand.narvatov.giphyapp.data.local.dao.base

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

interface BaseDao<T : Any> {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: T)

    @Insert(onConflict = REPLACE)
    suspend fun insert(items: List<T>)

    @JvmSuppressWildcards
    suspend fun clear()

}