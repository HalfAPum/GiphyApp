package oleksand.narvatov.giphyapp.data.remote.helper

import oleksand.narvatov.giphyapp.data.remote.api.GiphyApi
import oleksand.narvatov.giphyapp.data.remote.mapper.toLocalGiphy
import oleksand.narvatov.giphyapp.model.local.Giphy
import javax.inject.Inject

class GiphyApiHelper @Inject constructor(
    private val giphyApi: GiphyApi,
) {

    suspend fun searchGifs(
        query: String,
        offset: Int,
        limit: Int,
    ): List<Giphy> {
        val apiResult = giphyApi.searchGifs(query, offset, limit)
        return apiResult.toLocalGiphy()
    }

}