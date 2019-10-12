package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.data.db.MoviesDao
import my.clean.data.db.MoviesDatabase
import my.clean.data.entities.MovieDataEntityMapper
import my.clean.data.entities.MovieEntityDataMapper
import my.clean.domian.entities.MovieEntity

class MoviesCacheImpl(
    database: MoviesDatabase,
    private val entityToDataMapper: MovieEntityDataMapper,
    private val dataToEntityMapper: MovieDataEntityMapper
) : MoviesDataStore {

    private val dao: MoviesDao = database.getMoviesDao()

    override fun getMovies(): Flowable<List<MovieEntity>> {
        return dao.getAllMovies()
            .flatMapIterable { it }
            .map { dataToEntityMapper.mapToEntity(it) }
            .toList()
            .toFlowable()
    }

    fun saveMovies(list: List<MovieEntity>) = dao.run {
        clear()
        saveAllMovies(list.map { entityToDataMapper.mapToData(it) })
    }
}