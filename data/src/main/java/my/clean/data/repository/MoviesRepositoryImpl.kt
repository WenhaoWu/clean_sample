package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.domian.entities.MovieEntity
import my.clean.domian.repositories.MoviesRepository

class MoviesRepositoryImpl(
    private val remote: MoviesRemoteImpl,
    private val cache: MoviesCacheImpl
) : MoviesRepository {

    override fun getMovies(): Flowable<List<MovieEntity>> {
        val updateFlow = remote.getMovies()
        return cache.getMovies()
            .mergeWith(updateFlow.doOnNext { movies -> cache.saveMovies(movies) })
    }

    override fun getLocalMovies() = cache.getMovies()

    override fun getRemoteMovies() = remote.getMovies()
}