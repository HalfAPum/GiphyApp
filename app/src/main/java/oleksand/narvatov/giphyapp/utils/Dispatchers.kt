package oleksand.narvatov.giphyapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class Dispatchers(
    val IO: CoroutineDispatcher = Dispatchers.IO,
    val Default: CoroutineDispatcher = Dispatchers.Default,
    val Unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
    val Main: CoroutineDispatcher = Dispatchers.Main,
)