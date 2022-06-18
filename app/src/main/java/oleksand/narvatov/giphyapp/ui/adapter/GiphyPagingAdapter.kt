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

    var onDeleteListener: (Giphy) -> Unit = {}

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        getItem(position)?.let { giphy ->
            holder.update(giphy)
            holder.setOnDeleteListener {
                onDeleteListener(giphy)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = GiphyViewHolder.create(parent)

}