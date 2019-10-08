package my.clean.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import my.clean.data.entities.MovieData

@Dao
interface MoviesDao {
    @Query("Select * from movies")
    fun getAllMovies(): Flowable<List<MovieData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movies: List<MovieData>)

    @Query("Delete FROM movies")
    fun clear()
}