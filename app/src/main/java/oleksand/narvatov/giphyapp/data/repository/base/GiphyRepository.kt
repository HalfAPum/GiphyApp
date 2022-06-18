package oleksand.narvatov.giphyapp.data.repository.base

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import oleksand.narvatov.giphyapp.model.local.Giphy

interface GiphyRepository {

    fun searchGifs(
        query: String,
        config: PagingConfig,
    ) : Flow<PagingData<Giphy>>

    suspend fun clearKeys()

    suspend fun getRemovedGifsIds(): List<String>

    suspend fun removeGif(giphy: Giphy)

}