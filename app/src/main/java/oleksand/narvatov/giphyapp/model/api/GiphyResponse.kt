package oleksand.narvatov.giphyapp.model.api

import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @SerializedName("data")
    val giphyItems: List<GiphyItem>,
    // TODO: Add errors data
)

data class GiphyItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
)