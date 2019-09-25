package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.domian.entities.NewsSourcesEntity
import my.clean.domian.repositories.NewsRepository

class NewsRepositoryImpl(
    private val remote: NewsRemoteImpl,
    private val cache: NewsCacheImpl
) : NewsRepository {

    override fun getNews(): Flowable<NewsSourcesEntity> {
        val updateNewsFlow = remote.getNews()
        return cache.getNews()
            .mergeWith(updateNewsFlow
                .doOnNext { remoteNews ->
                    cache.saveArticles(remoteNews)
                })
    }

    override fun getLocalNews() = cache.getNews()

    override fun getRemoteNews() = remote.getNews()
}