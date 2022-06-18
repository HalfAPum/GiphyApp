package oleksand.narvatov.giphyapp.utils

import android.widget.ImageView
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import timber.log.Timber

fun ImageView.loadGif(
    url: String
) {
    Glide
        .with(context)
        .asGif()
        .load(url)
        .into(this)
}

@Suppress("FunctionName")
fun <T : Any> MutablePagingFlow() = lazy(LazyThreadSafetyMode.NONE) {
    MutablePagingSharedFlow<PagingData<T>>()
}

@Suppress("FunctionName")
private fun <T : Any> MutablePagingSharedFlow() = MutableSharedFlow<T>(
    replay = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
)

val errorHandler = CoroutineExceptionHandler { _, throwable ->
    Timber.d(throwable)
}

fun CombinedLoadStates.finishedLoadFromServer(): Boolean {
    return refresh.endOfPaginationReached.not()
        && refresh is LoadState.NotLoading

        && prepend.endOfPaginationReached
        && prepend is LoadState.NotLoading

        && append.endOfPaginationReached.not()
        && append is LoadState.NotLoading


        && source.refresh.endOfPaginationReached.not()
        && source.refresh is LoadState.NotLoading

        && source.prepend.endOfPaginationReached
        && source.prepend is LoadState.NotLoading

        && source.append.endOfPaginationReached.not()
        && source.append is LoadState.NotLoading


        && mediator?.prepend?.endOfPaginationReached == true
        && mediator?.prepend is LoadState.NotLoading
}

fun CombinedLoadStates.finishedLoadFromCache(): Boolean {
    return append.endOfPaginationReached
        && append is LoadState.NotLoading
}