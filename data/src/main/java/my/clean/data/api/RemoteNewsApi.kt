package my.clean.data.api

import io.reactivex.Flowable
import my.clean.data.entities.NewsSourcesData
import retrofit2.http.GET

interface RemoteNewsApi {
    @GET("top-headlines?country=us")
    fun getNews(): Flowable<NewsSourcesData>
}