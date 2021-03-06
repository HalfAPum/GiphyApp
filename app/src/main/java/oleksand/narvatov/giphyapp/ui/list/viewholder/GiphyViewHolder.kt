package oleksand.narvatov.giphyapp.ui.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import oleksand.narvatov.giphyapp.databinding.GiphyItemLayoutBinding
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.utils.loadGif


class GiphyViewHolder(private val binding: GiphyItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun update(item: Giphy) {
        binding.gifView.loadGif(item.url)
    }

    fun setOnDeleteListener(listener: () -> Unit) {
        binding.deleteButton.setOnClickListener { listener() }
    }

    fun setOnClickListener(listener: () -> Unit) {
        binding.root.setOnClickListener { listener() }
    }

    companion object {

        fun create(parent: ViewGroup) = GiphyViewHolder(
            GiphyItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}