package oleksand.narvatov.giphyapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import oleksand.narvatov.giphyapp.R
import oleksand.narvatov.giphyapp.databinding.FragmentGifListBinding
import oleksand.narvatov.giphyapp.ui.list.adapter.GiphyPagingAdapter
import oleksand.narvatov.giphyapp.ui.list.decorator.GridItemDecorator
import oleksand.narvatov.giphyapp.ui.viewmodel.GiphyListViewModel
import oleksand.narvatov.giphyapp.ui.viewmodel.SharedScrollViewModel
import oleksand.narvatov.giphyapp.utils.finishedLoadFromCache
import oleksand.narvatov.giphyapp.utils.finishedLoadFromServer
import javax.inject.Inject

@AndroidEntryPoint
class GifListFragment : Fragment(R.layout.fragment_gif_list) {

    private val binding: FragmentGifListBinding by viewBinding()

    private val viewModel: GiphyListViewModel by viewModels()
    private val scrollViewModel: SharedScrollViewModel by activityViewModels()

    @Inject
    lateinit var giphyPagingAdapter: GiphyPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
        initViews()
    }

    private fun initViewModels() {
        lifecycleScope.launchWhenStarted {
            viewModel.pagingData.collectLatest { pagingData ->
                giphyPagingAdapter.submitData(lifecycle, pagingData)
            }
        }

        viewModel.searchGifs()
    }

    private fun initViews() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                scrollViewModel.resetScroll()
                viewModel.searchGifs(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        with(binding.recyclerView) {
            addItemDecoration(
                GridItemDecorator(
                    GRID_SIZE,
                    resources.getDimensionPixelOffset(R.dimen.medium_padding)
                )
            )
            layoutManager = GridLayoutManager(requireContext(), GRID_SIZE)
            adapter = giphyPagingAdapter
        }

        with(giphyPagingAdapter) {
            giphyPagingAdapter.addLoadStateListener {
                if(it.finishedLoadFromServer() || it.finishedLoadFromCache()) {
                    val selectedPosition = scrollViewModel.scrollPositionStateFlow.value
                    binding.recyclerView.scrollToPosition(selectedPosition)
                }
            }

            onDeleteListener = { viewModel.removeGif(it) }
            onClickListener = ::navigateLargeGifScreen
        }
    }

    private fun navigateLargeGifScreen(position: Int) {
        scrollViewModel.saveScroll(position)

        findNavController().navigate(
            GifListFragmentDirections.toLargeGif(
                binding.searchView.query.toString()
            )
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val selectedPosition = (binding.recyclerView.layoutManager as GridLayoutManager)
            .findFirstCompletelyVisibleItemPosition()
        scrollViewModel.saveScroll(selectedPosition)
    }

    companion object {
        private const val GRID_SIZE = 2
    }

}