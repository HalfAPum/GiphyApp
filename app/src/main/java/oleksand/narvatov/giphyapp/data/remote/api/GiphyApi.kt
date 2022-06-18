package oleksand.narvatov.giphyapp.data.remote.api

import oleksand.narvatov.giphyapp.model.api.GiphyResponse
import oleksand.narvatov.giphyapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("v1/gifs/search")
    suspend fun searchGifs(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ) : GiphyResponse

}