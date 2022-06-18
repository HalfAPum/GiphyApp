package oleksand.narvatov.giphyapp.ui.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import oleksand.narvatov.giphyapp.databinding.GiphyItemLayoutBinding
import oleksand.narvatov.giphyapp.databinding.LargeGiphyItemLayoutBinding
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.utils.loadGif

class LargeGiphyViewHolder(private val binding: LargeGiphyItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun update(item: Giphy) {
        binding.gifView.loadGif(item.url)
    }

    companion object {

        fun create(parent: ViewGroup) = LargeGiphyViewHolder(
            LargeGiphyItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}