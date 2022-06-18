package oleksand.narvatov.giphyapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import oleksand.narvatov.giphyapp.R
import oleksand.narvatov.giphyapp.databinding.FragmentLargeGifBinding
import oleksand.narvatov.giphyapp.ui.list.adapter.LargeGiphyPagingAdapter
import oleksand.narvatov.giphyapp.ui.viewmodel.GiphyListViewModel
import oleksand.narvatov.giphyapp.ui.viewmodel.SharedScrollViewModel
import oleksand.narvatov.giphyapp.utils.finishedLoadFromCache
import oleksand.narvatov.giphyapp.utils.finishedLoadFromServer
import javax.inject.Inject

@AndroidEntryPoint
class LargeGifFragment : Fragment(R.layout.fragment_large_gif) {

    private val binding: FragmentLargeGifBinding by viewBinding()

    private val viewModel: GiphyListViewModel by viewModels()
    private val scrollViewModel: SharedScrollViewModel by activityViewModels()

    private val args: LargeGifFragmentArgs by navArgs()

    @Inject
    lateinit var largeGiphyPagingAdapter: LargeGiphyPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
        initViews()
    }

    private fun initViewModels() {
        lifecycleScope.launchWhenStarted {
            viewModel.pagingData.collectLatest { pagingData ->
                largeGiphyPagingAdapter.submitData(lifecycle, pagingData)
            }
        }

        viewModel.searchGifs(args.query)
    }

    private fun initViews() {
        with(binding.viewPager) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = largeGiphyPagingAdapter

            largeGiphyPagingAdapter.addLoadStateListener {
                if(it.finishedLoadFromServer() || it.finishedLoadFromCache()) {
                    val selectedPosition = scrollViewModel.scrollPositionStateFlow.value
                    binding.viewPager.setCurrentItem(selectedPosition, false)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        scrollViewModel.saveScroll(binding.viewPager.currentItem)
    }

}