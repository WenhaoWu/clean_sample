package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.data.api.RemoteNewsApi
import my.clean.data.entities.NewsDataEntityMapper
import my.clean.domian.entities.NewsSourcesEntity

class NewsRemoteImpl constructor(private val api: RemoteNewsApi) : NewsDataStore {
    private val newsMapper = NewsDataEntityMapper()

    override fun getNews(): Flowable<NewsSourcesEntity> =
        api.getNews().map { newsMapper.mapToEntity(it) }
}