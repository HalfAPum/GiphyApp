package oleksand.narvatov.giphyapp.data.remote.helper.base

interface SearchPagingApiHelper<T> {

    suspend fun search(
        query: String,
        offset: Int,
        limit: Int,
    ): List<T>

}