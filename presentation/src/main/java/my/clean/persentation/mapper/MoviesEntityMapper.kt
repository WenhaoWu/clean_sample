package my.clean.persentation.mapper

import my.clean.domian.common.Mapper
import my.clean.domian.entities.MovieEntity
import my.clean.persentation.entities.Movie

class MoviesEntityMapper : Mapper<MovieEntity, Movie>() {
    override fun mapFrom(from: MovieEntity): Movie {
        return Movie(
            id = from.id,
            posterPath = from.posterPath,
            title = from.title,
            tagLine = from.tagLine,
            overview = from.overview,
            rate = from.rate,
            releaseDate = from.releaseDate
        )
    }

}