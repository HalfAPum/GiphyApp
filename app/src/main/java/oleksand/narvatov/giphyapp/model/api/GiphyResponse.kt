package oleksand.narvatov.giphyapp.model.api

import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @SerializedName("data")
    val giphyItems: List<GiphyItem>,
)

data class GiphyItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
)

data class Images(
    @SerializedName("preview_gif")
    val downsizedSmall: DownsizedSmall,
)

data class DownsizedSmall(
    @SerializedName("url")
    val url: String?,
)