package oleksand.narvatov.giphyapp.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.ui.diffutil.GiphyDiffUtil
import oleksand.narvatov.giphyapp.ui.viewholder.GiphyViewHolder
import javax.inject.Inject

class GiphyPagingAdapter @Inject constructor(
    giphyDiffUtil: GiphyDiffUtil
): PagingDataAdapter<Giphy, GiphyViewHolder>(giphyDiffUtil) {

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        getItem(position)?.let { holder.update(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = GiphyViewHolder.create(parent)

}