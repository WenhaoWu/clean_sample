package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.data.api.RemoteMoviesApi
import my.clean.data.entities.MovieDataEntityMapper
import my.clean.domian.entities.MovieEntity

class MoviesRemoteImpl constructor(private val api: RemoteMoviesApi) : MoviesDataStore {
    private val mapper = MovieDataEntityMapper()

    override fun getMovies(): Flowable<List<MovieEntity>> {
        return api.getMovies()
            .map { it.results }
            .flatMapIterable { it }
            .map { mapper.mapToEntity(it) }
            .toList()
            .toFlowable()
    }
}