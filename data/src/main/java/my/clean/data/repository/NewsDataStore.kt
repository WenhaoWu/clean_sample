package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.domian.entities.NewsSourcesEntity

interface NewsDataStore {
    fun getNews(): Flowable<NewsSourcesEntity>
}