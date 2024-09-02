package farhad.deghat.flickflop.movie_list.data.mapper

import farhad.deghat.flickflop.common.data.mapper.ToDomainMapper
import farhad.deghat.flickflop.movie_list.data.entity.MoviesPageEntity
import farhad.deghat.flickflop.movie_list.domain.model.MoviesPage
import javax.inject.Inject

class MoviePageEntityMapper @Inject constructor(
    private val movieEntityMapper: MovieEntityMapper,
): ToDomainMapper<MoviesPageEntity, MoviesPage> {
    override fun mapToDomain(from: MoviesPageEntity) = MoviesPage(
        page = from.page,
        movies = from.movies.map { movieEntity ->  movieEntityMapper.mapToDomain(movieEntity) },
        totalPages = from.totalPages,
        totalResults = from.totalResults,
    )
}