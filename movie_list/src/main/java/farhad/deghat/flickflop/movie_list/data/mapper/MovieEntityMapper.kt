package farhad.deghat.flickflop.movie_list.data.mapper

import farhad.deghat.flickflop.common.data.mapper.ToDomainMapper
import farhad.deghat.flickflop.movie_list.data.entity.MovieEntity
import farhad.deghat.flickflop.movie_list.domain.model.Movie
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : ToDomainMapper<MovieEntity, Movie> {
    override fun mapToDomain(from: MovieEntity) = Movie(
        overview = from.overview,
        posterPath = from.posterPath,
        title = from.title,
    )
}