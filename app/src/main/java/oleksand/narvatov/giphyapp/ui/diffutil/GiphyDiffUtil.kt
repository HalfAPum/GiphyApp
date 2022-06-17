package oleksand.narvatov.giphyapp.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import oleksand.narvatov.giphyapp.model.local.Giphy
import javax.inject.Inject

class GiphyDiffUtil @Inject constructor() : DiffUtil.ItemCallback<Giphy>() {

    override fun areItemsTheSame(oldItem: Giphy, newItem: Giphy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Giphy, newItem: Giphy): Boolean {
        return oldItem.url == newItem.url
    }

}