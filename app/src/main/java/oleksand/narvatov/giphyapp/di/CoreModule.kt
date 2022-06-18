package oleksand.narvatov.giphyapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import oleksand.narvatov.giphyapp.data.remote.helper.GiphySearchPagingApiHelper
import oleksand.narvatov.giphyapp.data.remote.helper.base.SearchPagingApiHelper
import oleksand.narvatov.giphyapp.data.repository.GiphyRepositoryImpl
import oleksand.narvatov.giphyapp.data.repository.base.GiphyRepository
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.utils.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindGiphyRepository(giphyRepositoryImpl: GiphyRepositoryImpl): GiphyRepository

    @Binds
    abstract fun bindSearchApiHelper(
        giphySearchPagingApiHelper: GiphySearchPagingApiHelper
    ): SearchPagingApiHelper<Giphy>

    companion object {
        @Provides
        fun provideDispatchers() = Dispatchers()

    }
}