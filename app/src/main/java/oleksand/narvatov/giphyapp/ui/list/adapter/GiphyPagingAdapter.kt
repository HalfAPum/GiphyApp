package oleksand.narvatov.giphyapp.ui.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.ui.list.diffutil.GiphyDiffUtil
import oleksand.narvatov.giphyapp.ui.list.viewholder.GiphyViewHolder
import javax.inject.Inject

class GiphyPagingAdapter @Inject constructor(
    giphyDiffUtil: GiphyDiffUtil
): PagingDataAdapter<Giphy, GiphyViewHolder>(giphyDiffUtil) {

    var onDeleteListener: (Giphy) -> Unit = {}
    var onClickListener: (Int) -> Unit = {}

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        getItem(position)?.let { giphy ->
            holder.update(giphy)
            holder.setOnDeleteListener {
                onDeleteListener(giphy)
            }
            holder.setOnClickListener {
                onClickListener(position)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = GiphyViewHolder.create(parent)

}