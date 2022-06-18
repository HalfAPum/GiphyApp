package oleksand.narvatov.giphyapp.di.factory

import dagger.assisted.AssistedFactory
import oleksand.narvatov.giphyapp.data.paging.GiphyRemoteMediator

@AssistedFactory
interface GiphyRemoteMediatorFactory {
    fun create(query: String): GiphyRemoteMediator
}