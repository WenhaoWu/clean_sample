package my.clean.domian.entities

data class NewsPublisherEntity(
    var id: Int,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null
)