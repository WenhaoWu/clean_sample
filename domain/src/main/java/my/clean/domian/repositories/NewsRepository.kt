package my.clean.domian.repositories

import io.reactivex.Flowable
import my.clean.domian.entities.NewsSourcesEntity

interface NewsRepository{

    fun getNews(): Flowable<NewsSourcesEntity>;
    fun getLocalNews(): Flowable<NewsSourcesEntity>;
    fun getRemoteNews(): Flowable<NewsSourcesEntity>;

}