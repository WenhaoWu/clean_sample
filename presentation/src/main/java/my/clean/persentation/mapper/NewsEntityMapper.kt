package my.clean.persentation.mapper

import my.clean.domian.common.Mapper
import my.clean.domian.entities.NewsPublisherEntity
import my.clean.domian.entities.NewsSourcesEntity
import my.clean.presentation.entities.NewsPublisher
import my.clean.presentation.entities.NewsSources

class NewsEntityMapper : Mapper<NewsSourcesEntity, NewsSources>() {
    override fun mapFrom(from: NewsSourcesEntity): NewsSources = NewsSources(
        status = from.status,
        articles = mapListArticlesToPresentation(from.articles)
    )

    private fun mapListArticlesToPresentation(articles: List<NewsPublisherEntity>): List<NewsPublisher> =
        articles.map { mapArticleToPresentation(it) }

    private fun mapArticleToPresentation(response: NewsPublisherEntity): NewsPublisher =
        NewsPublisher(
            id = response.id,
            title = response.title,
            description = response.description,
            url = response.url
        )
}