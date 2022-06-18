package oleksand.narvatov.giphyapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import oleksand.narvatov.giphyapp.data.local.AppDatabase
import oleksand.narvatov.giphyapp.data.local.TransactionManager
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemoteKeyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemovedGiphyDao
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
        ).build()
    }

    @Provides
    fun provideTransactionManager(appDatabase: AppDatabase): TransactionManager = appDatabase


    @Provides
    fun provideRemoteKeyDao(
        appDatabase: AppDatabase
    ): RemoteKeyDao = appDatabase.getRemoteKeyDao()

    @Provides
    fun provideGiphyDao(
        appDatabase: AppDatabase
    ): GiphyDao = appDatabase.getGiphyDao()

    @Provides
    fun provideRemovedGiphyDao(
        appDatabase: AppDatabase
    ): RemovedGiphyDao = appDatabase.getRemovedGiphyDao()

}