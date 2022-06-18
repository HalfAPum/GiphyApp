package oleksand.narvatov.giphyapp.data.repository.base

import oleksand.narvatov.giphyapp.utils.Dispatchers
import javax.inject.Inject

abstract class AbsRepository {

    @Inject
    lateinit var dispatchers: Dispatchers

}