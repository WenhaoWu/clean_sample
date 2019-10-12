package my.clean.domian.entities

data class MovieDetailEntity(
    var id: String? = null,
    var posterPath: String? = null,
    var title: String? = null,
    var tagLine: String? = null,
    var overview: String? = null,
    var genres: String? = null,
    var rate: Double?,
    var backdrops: List<String>? = emptyList(),
    var casts: List<CastEntity> = emptyList()
)