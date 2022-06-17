package oleksand.narvatov.giphyapp.data.remote.mapper

import oleksand.narvatov.giphyapp.model.api.GiphyItem
import oleksand.narvatov.giphyapp.model.api.GiphyResponse
import oleksand.narvatov.giphyapp.model.local.Giphy

fun GiphyResponse.toLocalGiphy() : List<Giphy> {
    return giphyItems.mapToGifs()
}

fun List<GiphyItem>.mapToGifs() = map { item ->
    Giphy(item.id, item.url)
}
