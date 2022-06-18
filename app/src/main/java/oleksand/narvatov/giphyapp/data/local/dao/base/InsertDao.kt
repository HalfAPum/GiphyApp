package oleksand.narvatov.giphyapp.data.local.dao.base

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface InsertDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<T>)

}