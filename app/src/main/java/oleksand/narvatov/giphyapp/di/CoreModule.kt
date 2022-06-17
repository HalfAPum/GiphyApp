package oleksand.narvatov.giphyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import oleksand.narvatov.giphyapp.utils.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideDispatchers() = Dispatchers()

}