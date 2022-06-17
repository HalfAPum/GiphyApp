package oleksand.narvatov.giphyapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import oleksand.narvatov.giphyapp.data.local.AppDatabase
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemoteKeyDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "GiphyDatabase"
            // TODO: remore destrucitve migrations
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    fun provideRemoteKeyDao(
        appDatabase: AppDatabase
    ): RemoteKeyDao = appDatabase.getRemoteKeyDao()

    @Provides
    fun provideGiphyDao(
        appDatabase: AppDatabase
    ): GiphyDao = appDatabase.getGiphyDao()

}