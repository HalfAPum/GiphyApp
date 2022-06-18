package oleksand.narvatov.giphyapp.data.local.dao.base

interface ClearDao {

    @JvmSuppressWildcards
    suspend fun clear()

}