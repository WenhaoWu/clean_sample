package my.clean.domian.repositories

import io.reactivex.Flowable
import my.clean.domian.entities.MovieEntity

interface MoviesRepository {
    fun getMovies(): Flowable<List<MovieEntity>>
    fun getLocalMovies(): Flowable<List<MovieEntity>>
    fun getRemoteMovies(): Flowable<List<MovieEntity>>
}