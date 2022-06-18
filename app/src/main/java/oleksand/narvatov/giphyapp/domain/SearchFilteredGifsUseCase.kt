package oleksand.narvatov.giphyapp.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import oleksand.narvatov.giphyapp.data.repository.GiphyRepository
import oleksand.narvatov.giphyapp.model.local.Giphy
import javax.inject.Inject

class SearchFilteredGifsUseCase @Inject constructor(
    private val giphyRepository: GiphyRepository,
) {

    suspend operator fun invoke(
        query: String,
        config: PagingConfig,
    ) : Flow<PagingData<Giphy>> {
        val removedGifsIds = giphyRepository.getRemovedGifsIds()

        return giphyRepository.searchGifs(
            query = query,
            config = config,
        ).map {
            it.filter { giphy ->
                removedGifsIds.contains(giphy.id).not()
            }
        }
    }

}