package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.domian.entities.MovieEntity

interface MoviesDataStore {
    fun getMovies(): Flowable<List<MovieEntity>>
}