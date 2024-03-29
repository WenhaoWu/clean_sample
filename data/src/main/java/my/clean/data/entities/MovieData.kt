package my.clean.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import my.clean.domian.entities.MovieEntity
import java.util.*

@Entity(tableName = "movies")
data class MovieData(
    @PrimaryKey
    @NonNull
    var id: String,
    @SerializedName("posterPath")
    var posterPath: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("tagLine")
    var tagLine: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("rate")
    var rate: Float?,
    @SerializedName("releaseDate")
    var releaseDate: String? = null
)

class MovieDataEntityMapper {
    fun mapToEntity(data: MovieData): MovieEntity = MovieEntity(
        id = data.id,
        posterPath = data.posterPath,
        title = data.title,
        tagLine = data.tagLine,
        overview = data.overview,
        rate = data.rate,
        releaseDate = data.releaseDate
    )
}

class MovieEntityDataMapper {
    fun mapToData(entity: MovieEntity): MovieData = MovieData(
        id = entity.id ?: UUID.randomUUID().toString(),
        posterPath = entity.posterPath,
        title = entity.title,
        tagLine = entity.tagLine,
        overview = entity.overview,
        rate = entity.rate,
        releaseDate = entity.releaseDate
    )
}