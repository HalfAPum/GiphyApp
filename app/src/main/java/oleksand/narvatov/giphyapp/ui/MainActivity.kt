package oleksand.narvatov.giphyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import oleksand.narvatov.giphyapp.R
import oleksand.narvatov.giphyapp.data.remote.api.GiphyApi
import oleksand.narvatov.giphyapp.databinding.ActivityMainBinding
import oleksand.narvatov.giphyapp.ui.adapter.GiphyPagingAdapter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    private val viewModel: GiphyListViewModel by viewModels()

    @Inject
    lateinit var giphyApi: GiphyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = GiphyPagingAdapter()
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            val giphyFlow = viewModel.searchGifs()
            giphyFlow.collectLatest { pagingData ->
                adapter.submitData(lifecycle, pagingData)
            }
        }
    }
}