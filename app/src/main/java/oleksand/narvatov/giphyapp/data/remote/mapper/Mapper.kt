package oleksand.narvatov.giphyapp.data.remote.mapper

import oleksand.narvatov.giphyapp.model.api.GiphyItem
import oleksand.narvatov.giphyapp.model.api.GiphyResponse
import oleksand.narvatov.giphyapp.model.local.Giphy

fun GiphyResponse.toLocalGiphy() : List<Giphy> {
    return giphyItems.mapToGifs()
}

fun List<GiphyItem>.mapToGifs() = map { item ->
    Giphy(item.id, item.images.downsizedSmall.url ?: "https://media4.giphy.com/media/3oEduLl9m8HCTNxIli/100w_s.gif?cid=611e702f8ambyydo9ekhw7dwob93zo3xotwuqqxjnpc6uw04&rid=100w_s.gif&ct=g")
}
