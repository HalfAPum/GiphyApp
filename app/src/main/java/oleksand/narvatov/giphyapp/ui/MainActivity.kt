package oleksand.narvatov.giphyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import oleksand.narvatov.giphyapp.R
import oleksand.narvatov.giphyapp.data.remote.api.GiphyApi
import oleksand.narvatov.giphyapp.databinding.ActivityMainBinding
import oleksand.narvatov.giphyapp.ui.adapter.GiphyPagingAdapter
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
        binding.recyclerView.adapter = giphyPagingAdapter
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
                viewModel.searchGifs(newText ?: "")
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}