package oleksand.narvatov.giphyapp.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import oleksand.narvatov.giphyapp.databinding.GiphyItemLayoutBinding
import oleksand.narvatov.giphyapp.model.local.Giphy
import timber.log.Timber

class GiphyViewHolder(private val binding: GiphyItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun update(item: Giphy) {
        Timber.d("tag1 WTF ${item}")
//        binding.passengerName.text = item.name
    }

    companion object {

        //todo di
        fun create(parent: ViewGroup) = GiphyViewHolder(
            GiphyItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}