package oleksand.narvatov.giphyapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.RemoteMediator

/**
 * Class represents [LoadType] result after handling.
 * Wraps either page or [RemoteMediator.MediatorResult.Success].
 */
@OptIn(ExperimentalPagingApi::class)
sealed class LoadTypeResult {

    class PageResult(val page: Int): LoadTypeResult()

    class MediatorSuccessResult(
        val success: RemoteMediator.MediatorResult.Success
    ): LoadTypeResult()

}