package oleksand.narvatov.giphyapp.ui.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.ui.list.diffutil.GiphyDiffUtil
import oleksand.narvatov.giphyapp.ui.list.viewholder.GiphyViewHolder
import oleksand.narvatov.giphyapp.ui.list.viewholder.LargeGiphyViewHolder
import javax.inject.Inject

class LargeGiphyPagingAdapter @Inject constructor(
    giphyDiffUtil: GiphyDiffUtil
): PagingDataAdapter<Giphy, LargeGiphyViewHolder>(giphyDiffUtil) {

    override fun onBindViewHolder(holder: LargeGiphyViewHolder, position: Int) {
        getItem(position)?.let { giphy ->
            holder.update(giphy)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = LargeGiphyViewHolder.create(parent)

}