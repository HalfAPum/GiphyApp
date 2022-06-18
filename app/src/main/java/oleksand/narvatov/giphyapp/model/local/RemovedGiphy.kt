package oleksand.narvatov.giphyapp.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemovedGiphy(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
)
