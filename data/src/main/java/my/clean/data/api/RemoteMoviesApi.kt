package my.clean.data.api

import io.reactivex.Flowable
import my.clean.data.entities.MovieData
import retrofit2.http.GET

interface RemoteMoviesApi {
    @GET("/discover/movie")
    fun getMovies(): Flowable<List<MovieData>>
}