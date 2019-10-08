package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.data.api.RemoteMoviesApi
import my.clean.data.entities.MovieDataEntityMapper
import my.clean.domian.entities.MovieEntity

//class NewsRemoteImpl constructor(private val api: RemoteNewsApi) : NewsDataStore {
//    private val newsMapper = NewsDataEntityMapper()
//
//    override fun getNews(): Flowable<NewsSourcesEntity> =
//        api.getNews().map { newsMapper.mapToEntity(it) }
//}

class MoviesRemoteImpl constructor(private val api: RemoteMoviesApi) : MoviesDataStore {
    private val mapper = MovieDataEntityMapper()

    override fun getMovies(): Flowable<List<MovieEntity>> {
        return api.getMovies()
            .flatMapIterable { it }
            .map { mapper.mapToEntity(it) }
            .toList()
            .toFlowable()
    }
}