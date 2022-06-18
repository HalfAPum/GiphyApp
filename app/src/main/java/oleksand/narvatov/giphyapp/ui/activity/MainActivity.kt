package oleksand.narvatov.giphyapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import oleksand.narvatov.giphyapp.R
import oleksand.narvatov.giphyapp.data.remote.api.GiphyApi
import oleksand.narvatov.giphyapp.databinding.ActivityMainBinding
import oleksand.narvatov.giphyapp.ui.list.adapter.GiphyPagingAdapter
import oleksand.narvatov.giphyapp.ui.list.decorator.GridItemDecorator
import oleksand.narvatov.giphyapp.ui.viewmodel.GiphyListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    private val viewModel: GiphyListViewModel by viewModels()

    @Inject
    lateinit var giphyApi: GiphyApi

    @Inject
    lateinit var giphyPagingAdapter: GiphyPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.recyclerView.addItemDecoration(
            GridItemDecorator(
                GRID_SIZE,
                resources.getDimensionPixelOffset(R.dimen.medium_padding)
            )
        )
        binding.recyclerView.layoutManager = GridLayoutManager(this, GRID_SIZE)
        binding.recyclerView.adapter = giphyPagingAdapter
        giphyPagingAdapter.onDeleteListener = {
            viewModel.removeGif(it)
        }
        viewModel.searchGifs()
        lifecycleScope.launchWhenStarted {
            viewModel.pagingData.collectLatest { pagingData ->
                giphyPagingAdapter.submitData(lifecycle, pagingData)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_gifs)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchGifs(newText ?: "Error")
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        private const val GRID_SIZE = 2
    }
}