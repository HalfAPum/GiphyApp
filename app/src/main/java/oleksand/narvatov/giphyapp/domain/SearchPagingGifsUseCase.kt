package oleksand.narvatov.giphyapp.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import oleksand.narvatov.giphyapp.data.repository.base.GiphyRepository
import oleksand.narvatov.giphyapp.model.local.Giphy
import javax.inject.Inject

class SearchPagingGifsUseCase @Inject constructor(
    private val searchFilteredGifsUseCase: SearchFilteredGifsUseCase,
    private val giphyRepository: GiphyRepository,
) {

    suspend operator fun invoke(
        query: String,
        pageSize: Int = DEFAULT_PAGE_SIZE,
    ) : Flow<PagingData<Giphy>> {
        if (query.isNotBlank()) {
            giphyRepository.clearKeys()
        }

        return searchFilteredGifsUseCase(
            query = query,
            config = PagingConfig(pageSize)
        )
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}