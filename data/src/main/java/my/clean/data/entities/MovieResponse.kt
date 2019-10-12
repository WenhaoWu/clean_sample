package my.clean.data.entities

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    var posterPath: Int?,
    @SerializedName("results")
    var results: List<MovieData>? = emptyList()
)