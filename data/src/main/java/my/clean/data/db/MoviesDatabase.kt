package my.clean.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import my.clean.data.entities.MovieData

@Database(entities = arrayOf(MovieData::class), version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}