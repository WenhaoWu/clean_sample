package my.clean.persentation.entities

data class Movie(
    var id: String? = null,
    var posterPath: String? = null,
    var title: String? = null,
    var tagLine: String? = null,
    var overview: String? = null,
    var rate: Float?,
    var releaseDate: String? = null
)