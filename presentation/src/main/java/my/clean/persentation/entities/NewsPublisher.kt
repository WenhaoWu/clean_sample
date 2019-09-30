package my.clean.presentation.entities

data class NewsPublisher(
    var id: Int,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var category: String? = null
)