package oleksand.narvatov.giphyapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import androidx.core.content.ContextCompat.getSystemService
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