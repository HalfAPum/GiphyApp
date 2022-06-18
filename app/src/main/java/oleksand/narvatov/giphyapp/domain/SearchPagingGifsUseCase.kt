package oleksand.narvatov.giphyapp.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import oleksand.narvatov.giphyapp.data.repository.GiphyRepository
import oleksand.narvatov.giphyapp.model.local.Giphy
import javax.inject.Inject

class SearchPagingGifsUseCase @Inject constructor(
    private val giphyRepository: GiphyRepository,
) {

    suspend operator fun invoke(
        query: String,
        pageSize: Int = DEFAULT_PAGE_SIZE,
    ) : Flow<PagingData<Giphy>> {
        if (query.isNotBlank()) {
            giphyRepository.clearKeys()
        }

        val removedGifsIds = giphyRepository.getRemovedGifsIds()

        return giphyRepository.searchGifs(
            query,
            PagingConfig(pageSize)
        ).map {
            it.filter { giphy ->
                removedGifsIds.contains(giphy.id).not()
            }
        }
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}