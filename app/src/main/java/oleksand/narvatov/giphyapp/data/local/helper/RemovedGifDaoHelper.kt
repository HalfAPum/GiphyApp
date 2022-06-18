package oleksand.narvatov.giphyapp.data.local.helper

import oleksand.narvatov.giphyapp.data.local.TransactionManager
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemovedGiphyDao
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.model.local.RemovedGiphy
import javax.inject.Inject

class GetRemovedGifsIdsDaoHelper @Inject constructor(
    private val removedGiphyDao: RemovedGiphyDao,
) {

    suspend operator fun invoke() = removedGiphyDao.getAll().map { it.id }

}

class RemoveGifDaoHelper @Inject constructor(
    private val removedGiphyDao: RemovedGiphyDao,
    private val giphyDao: GiphyDao,
    private val transactionManager: TransactionManager,
) {

    suspend operator fun invoke(giphy: Giphy) {
        transactionManager.runInTransaction {
            removedGiphyDao.insert(RemovedGiphy(giphy.id))
            giphyDao.remove(giphy)
        }
    }

}