package oleksand.narvatov.giphyapp.data.local

interface TransactionManager {

    suspend fun runInTransaction(action: suspend () -> Unit)

}