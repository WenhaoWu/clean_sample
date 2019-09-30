package my.clean.data.entities

import com.google.gson.annotations.SerializedName
import my.clean.domian.entities.NewsPublisherEntity
import my.clean.domian.entities.NewsSourcesEntity

data class NewsSourcesData(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("articles")
    var articles: List<NewsPublisherData> = emptyList()
)

class NewsDataEntityMapper {
    fun mapToEntity(data: NewsSourcesData?): NewsSourcesEntity? = NewsSourcesEntity(
        status = data?.status,
        articles = mapListArticlesToEntity(data?.articles)
    )

    fun mapToEntity(articles: List<NewsPublisherData>?): NewsSourcesEntity? = NewsSourcesEntity(
        articles = mapListArticlesToEntity(articles)
    )

    fun mapListArticlesToEntity(articles: List<NewsPublisherData>?)
            : List<NewsPublisherEntity> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: NewsPublisherData): NewsPublisherEntity = NewsPublisherEntity(
        id = response.id,
        title = response.title,
        description = response.description
    )
}

class NewsEntityDataMapper {

    fun mapToEntity(data: NewsSourcesEntity?): NewsSourcesData? = NewsSourcesData(
        status = data?.status,
        articles = mapListArticlesToEntity(data?.articles)
    )

    fun mapListArticlesToEntity(articles: List<NewsPublisherEntity>?)
            : List<NewsPublisherData> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: NewsPublisherEntity): NewsPublisherData = NewsPublisherData(
        id = response.id,
        title = response.title,
        description = response.description,
        url = response.url
    )

}